package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableAppointmentSameNumber;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableAppointmentSameNumber entities.
 * 
 */
@Repository("TimetableAppointmentSameNumberDAO")
@Transactional
public class TimetableAppointmentSameNumberDAOImpl extends AbstractJpaDao<TimetableAppointmentSameNumber>
		implements TimetableAppointmentSameNumberDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableAppointmentSameNumber.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableAppointmentSameNumberDAOImpl
	 *
	 */
	public TimetableAppointmentSameNumberDAOImpl() {
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
	 * JPQL Query - findTimetableAppointmentSameNumberByUpdatedBy
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByUpdatedBy(Integer updatedBy) throws DataAccessException {

		return findTimetableAppointmentSameNumberByUpdatedBy(updatedBy, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByUpdatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByUpdatedBy(Integer updatedBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByUpdatedBy", startResult, maxRows, updatedBy);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalWeeksContaining
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalWeeksContaining(String totalWeeks) throws DataAccessException {

		return findTimetableAppointmentSameNumberByTotalWeeksContaining(totalWeeks, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalWeeksContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalWeeksContaining(String totalWeeks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByTotalWeeksContaining", startResult, maxRows, totalWeeks);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTimetableAppointmentSameNumbers
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findAllTimetableAppointmentSameNumbers() throws DataAccessException {

		return findAllTimetableAppointmentSameNumbers(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableAppointmentSameNumbers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findAllTimetableAppointmentSameNumbers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableAppointmentSameNumbers", startResult, maxRows);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByEndClass
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByEndClass(Integer endClass) throws DataAccessException {

		return findTimetableAppointmentSameNumberByEndClass(endClass, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByEndClass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByEndClass(Integer endClass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByEndClass", startResult, maxRows, endClass);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByEndWeek
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByEndWeek(Integer endWeek) throws DataAccessException {

		return findTimetableAppointmentSameNumberByEndWeek(endWeek, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByEndWeek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByEndWeek(Integer endWeek, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByEndWeek", startResult, maxRows, endWeek);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByStartClass
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByStartClass(Integer startClass) throws DataAccessException {

		return findTimetableAppointmentSameNumberByStartClass(startClass, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByStartClass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByStartClass(Integer startClass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByStartClass", startResult, maxRows, startClass);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByUpdatedDate
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByUpdatedDate(java.util.Calendar updatedDate) throws DataAccessException {

		return findTimetableAppointmentSameNumberByUpdatedDate(updatedDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByUpdatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByUpdatedDate(java.util.Calendar updatedDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByUpdatedDate", startResult, maxRows, updatedDate);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalWeeks
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalWeeks(String totalWeeks) throws DataAccessException {

		return findTimetableAppointmentSameNumberByTotalWeeks(totalWeeks, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalWeeks
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalWeeks(String totalWeeks, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByTotalWeeks", startResult, maxRows, totalWeeks);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberById
	 *
	 */
	@Transactional
	public TimetableAppointmentSameNumber findTimetableAppointmentSameNumberById(Integer id) throws DataAccessException {

		return findTimetableAppointmentSameNumberById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberById
	 *
	 */

	@Transactional
	public TimetableAppointmentSameNumber findTimetableAppointmentSameNumberById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableAppointmentSameNumberById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableAppointmentSameNumber) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalClasses
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalClasses(Integer totalClasses) throws DataAccessException {

		return findTimetableAppointmentSameNumberByTotalClasses(totalClasses, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByTotalClasses
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByTotalClasses(Integer totalClasses, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByTotalClasses", startResult, maxRows, totalClasses);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByCreatedBy
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByCreatedBy(Integer createdBy) throws DataAccessException {

		return findTimetableAppointmentSameNumberByCreatedBy(createdBy, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByCreatedBy
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByCreatedBy(Integer createdBy, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByCreatedBy", startResult, maxRows, createdBy);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableAppointmentSameNumber findTimetableAppointmentSameNumberByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableAppointmentSameNumberByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableAppointmentSameNumber findTimetableAppointmentSameNumberByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableAppointmentSameNumberByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableAppointmentSameNumber) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByWeekday
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByWeekday(Integer weekday) throws DataAccessException {

		return findTimetableAppointmentSameNumberByWeekday(weekday, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByWeekday
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByWeekday", startResult, maxRows, weekday);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByCreatedDate
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByCreatedDate(java.util.Calendar createdDate) throws DataAccessException {

		return findTimetableAppointmentSameNumberByCreatedDate(createdDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByCreatedDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByCreatedDate(java.util.Calendar createdDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByCreatedDate", startResult, maxRows, createdDate);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByStartWeek
	 *
	 */
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByStartWeek(Integer startWeek) throws DataAccessException {

		return findTimetableAppointmentSameNumberByStartWeek(startWeek, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableAppointmentSameNumberByStartWeek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableAppointmentSameNumber> findTimetableAppointmentSameNumberByStartWeek(Integer startWeek, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableAppointmentSameNumberByStartWeek", startResult, maxRows, startWeek);
		return new LinkedHashSet<TimetableAppointmentSameNumber>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TimetableAppointmentSameNumber entity) {
		return true;
	}
}
