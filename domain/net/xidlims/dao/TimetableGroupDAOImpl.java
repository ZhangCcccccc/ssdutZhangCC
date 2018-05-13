package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableGroup;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableGroup entities.
 * 
 */
@Repository("TimetableGroupDAO")
@Transactional
public class TimetableGroupDAOImpl extends AbstractJpaDao<TimetableGroup>
		implements TimetableGroupDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableGroup.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableGroupDAOImpl
	 *
	 */
	public TimetableGroupDAOImpl() {
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
	 * JPQL Query - findTimetableGroupByGroupName
	 *
	 */
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByGroupName(String groupName) throws DataAccessException {

		return findTimetableGroupByGroupName(groupName, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableGroupByGroupName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByGroupName(String groupName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableGroupByGroupName", startResult, maxRows, groupName);
		return new LinkedHashSet<TimetableGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableGroupById
	 *
	 */
	@Transactional
	public TimetableGroup findTimetableGroupById(Integer id) throws DataAccessException {

		return findTimetableGroupById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableGroupById
	 *
	 */

	@Transactional
	public TimetableGroup findTimetableGroupById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableGroupById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableGroup) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTimetableGroups
	 *
	 */
	@Transactional
	public Set<TimetableGroup> findAllTimetableGroups() throws DataAccessException {

		return findAllTimetableGroups(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableGroups
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableGroup> findAllTimetableGroups(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableGroups", startResult, maxRows);
		return new LinkedHashSet<TimetableGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableGroupByNumbers
	 *
	 */
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByNumbers(Integer numbers) throws DataAccessException {

		return findTimetableGroupByNumbers(numbers, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableGroupByNumbers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByNumbers(Integer numbers, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableGroupByNumbers", startResult, maxRows, numbers);
		return new LinkedHashSet<TimetableGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableGroupByIfselect
	 *
	 */
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByIfselect(Integer ifselect) throws DataAccessException {

		return findTimetableGroupByIfselect(ifselect, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableGroupByIfselect
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByIfselect(Integer ifselect, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableGroupByIfselect", startResult, maxRows, ifselect);
		return new LinkedHashSet<TimetableGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableGroupByStartDate
	 *
	 */
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByStartDate(java.util.Calendar startDate) throws DataAccessException {

		return findTimetableGroupByStartDate(startDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableGroupByStartDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByStartDate(java.util.Calendar startDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableGroupByStartDate", startResult, maxRows, startDate);
		return new LinkedHashSet<TimetableGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableGroupByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableGroup findTimetableGroupByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableGroupByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableGroupByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableGroup findTimetableGroupByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableGroupByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableGroup) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableGroupByGroupNameContaining
	 *
	 */
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByGroupNameContaining(String groupName) throws DataAccessException {

		return findTimetableGroupByGroupNameContaining(groupName, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableGroupByGroupNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByGroupNameContaining(String groupName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableGroupByGroupNameContaining", startResult, maxRows, groupName);
		return new LinkedHashSet<TimetableGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableGroupByEndDate
	 *
	 */
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByEndDate(java.util.Calendar endDate) throws DataAccessException {

		return findTimetableGroupByEndDate(endDate, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableGroupByEndDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableGroup> findTimetableGroupByEndDate(java.util.Calendar endDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTimetableGroupByEndDate", startResult, maxRows, endDate);
		return new LinkedHashSet<TimetableGroup>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TimetableGroup entity) {
		return true;
	}
}
