package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.SystemTime;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage SystemTime entities.
 * 
 */
@Repository("SystemTimeDAO")
@Transactional
public class SystemTimeDAOImpl extends AbstractJpaDao<SystemTime> implements
		SystemTimeDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SystemTime.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new SystemTimeDAOImpl
	 *
	 */
	public SystemTimeDAOImpl() {
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
	 * JPQL Query - findSystemTimeBySequenceContaining
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeBySequenceContaining(String sequence) throws DataAccessException {

		return findSystemTimeBySequenceContaining(sequence, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeBySequenceContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeBySequenceContaining(String sequence, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeBySequenceContaining", startResult, maxRows, sequence);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByTimeNameContaining
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByTimeNameContaining(String timeName) throws DataAccessException {

		return findSystemTimeByTimeNameContaining(timeName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByTimeNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByTimeNameContaining(String timeName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByTimeNameContaining", startResult, maxRows, timeName);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByCreatedByContaining
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByCreatedByContaining(String createdBy) throws DataAccessException {

		return findSystemTimeByCreatedByContaining(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByCreatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByCreatedByContaining(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByCreatedByContaining", startResult, maxRows, createdBy);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByCombineContaining
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByCombineContaining(String combine) throws DataAccessException {

		return findSystemTimeByCombineContaining(combine, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByCombineContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByCombineContaining(String combine, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByCombineContaining", startResult, maxRows, combine);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByUpdatedByContaining
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByUpdatedByContaining(String updatedBy) throws DataAccessException {

		return findSystemTimeByUpdatedByContaining(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByUpdatedByContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByUpdatedByContaining(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByUpdatedByContaining", startResult, maxRows, updatedBy);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByStartDate
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByStartDate(java.util.Calendar startDate) throws DataAccessException {

		return findSystemTimeByStartDate(startDate, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByStartDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByStartDate(java.util.Calendar startDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByStartDate", startResult, maxRows, startDate);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeBySequence
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeBySequence(String sequence) throws DataAccessException {

		return findSystemTimeBySequence(sequence, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeBySequence
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeBySequence(String sequence, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeBySequence", startResult, maxRows, sequence);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByUpdatedBy
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByUpdatedBy(String updatedBy) throws DataAccessException {

		return findSystemTimeByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByUpdatedBy(String updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByPrimaryKey
	 *
	 */
	@Transactional
	public SystemTime findSystemTimeByPrimaryKey(Integer id) throws DataAccessException {

		return findSystemTimeByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByPrimaryKey
	 *
	 */

	@Transactional
	public SystemTime findSystemTimeByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemTimeByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.SystemTime) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemTimeById
	 *
	 */
	@Transactional
	public SystemTime findSystemTimeById(Integer id) throws DataAccessException {

		return findSystemTimeById(id, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeById
	 *
	 */

	@Transactional
	public SystemTime findSystemTimeById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findSystemTimeById", startResult, maxRows, id);
			return (net.xidlims.domain.SystemTime) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findSystemTimeByCombine
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByCombine(String combine) throws DataAccessException {

		return findSystemTimeByCombine(combine, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByCombine
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByCombine(String combine, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByCombine", startResult, maxRows, combine);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByCreatedBy
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByCreatedBy(String createdBy) throws DataAccessException {

		return findSystemTimeByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByCreatedBy(String createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByCreatedDate
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findSystemTimeByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllSystemTimes
	 *
	 */
	@Transactional
	public Set<SystemTime> findAllSystemTimes() throws DataAccessException {

		return findAllSystemTimes(-1, -1);
	}

	/**
	 * JPQL Query - findAllSystemTimes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findAllSystemTimes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllSystemTimes", startResult, maxRows);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByUpdatedDate
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findSystemTimeByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByEndDate
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByEndDate(java.util.Calendar endDate) throws DataAccessException {

		return findSystemTimeByEndDate(endDate, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByEndDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByEndDate(java.util.Calendar endDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByEndDate", startResult, maxRows, endDate);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findSystemTimeByTimeName
	 *
	 */
	@Transactional
	public Set<SystemTime> findSystemTimeByTimeName(String timeName) throws DataAccessException {

		return findSystemTimeByTimeName(timeName, -1, -1);
	}

	/**
	 * JPQL Query - findSystemTimeByTimeName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<SystemTime> findSystemTimeByTimeName(String timeName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findSystemTimeByTimeName", startResult, maxRows, timeName);
		return new LinkedHashSet<SystemTime>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(SystemTime entity) {
		return true;
	}
}
