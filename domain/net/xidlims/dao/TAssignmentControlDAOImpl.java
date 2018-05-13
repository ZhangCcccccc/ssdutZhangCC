package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TAssignmentControl;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TAssignmentControl entities.
 * 
 */
@Repository("TAssignmentControlDAO")
@Transactional
public class TAssignmentControlDAOImpl extends AbstractJpaDao<TAssignmentControl>
		implements TAssignmentControlDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TAssignmentControl.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TAssignmentControlDAOImpl
	 *
	 */
	public TAssignmentControlDAOImpl() {
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
	 * JPQL Query - findTAssignmentControlBySubmessage
	 *
	 */
	@Transactional
	public Set<TAssignmentControl> findTAssignmentControlBySubmessage(String submessage) throws DataAccessException {

		return findTAssignmentControlBySubmessage(submessage, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentControlBySubmessage
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentControl> findTAssignmentControlBySubmessage(String submessage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentControlBySubmessage", startResult, maxRows, submessage);
		return new LinkedHashSet<TAssignmentControl>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentControlByStartdate
	 *
	 */
	@Transactional
	public Set<TAssignmentControl> findTAssignmentControlByStartdate(java.util.Calendar startdate) throws DataAccessException {

		return findTAssignmentControlByStartdate(startdate, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentControlByStartdate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentControl> findTAssignmentControlByStartdate(java.util.Calendar startdate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentControlByStartdate", startResult, maxRows, startdate);
		return new LinkedHashSet<TAssignmentControl>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentControlByAssignmentId
	 *
	 */
	@Transactional
	public TAssignmentControl findTAssignmentControlByAssignmentId(Integer assignmentId) throws DataAccessException {

		return findTAssignmentControlByAssignmentId(assignmentId, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentControlByAssignmentId
	 *
	 */

	@Transactional
	public TAssignmentControl findTAssignmentControlByAssignmentId(Integer assignmentId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentControlByAssignmentId", startResult, maxRows, assignmentId);
			return (net.xidlims.domain.TAssignmentControl) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentControlByPrimaryKey
	 *
	 */
	@Transactional
	public TAssignmentControl findTAssignmentControlByPrimaryKey(Integer assignmentId) throws DataAccessException {

		return findTAssignmentControlByPrimaryKey(assignmentId, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentControlByPrimaryKey
	 *
	 */

	@Transactional
	public TAssignmentControl findTAssignmentControlByPrimaryKey(Integer assignmentId, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTAssignmentControlByPrimaryKey", startResult, maxRows, assignmentId);
			return (net.xidlims.domain.TAssignmentControl) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTAssignmentControlByDuedate
	 *
	 */
	@Transactional
	public Set<TAssignmentControl> findTAssignmentControlByDuedate(java.util.Calendar duedate) throws DataAccessException {

		return findTAssignmentControlByDuedate(duedate, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentControlByDuedate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentControl> findTAssignmentControlByDuedate(java.util.Calendar duedate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentControlByDuedate", startResult, maxRows, duedate);
		return new LinkedHashSet<TAssignmentControl>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentControlBySubmessageContaining
	 *
	 */
	@Transactional
	public Set<TAssignmentControl> findTAssignmentControlBySubmessageContaining(String submessage) throws DataAccessException {

		return findTAssignmentControlBySubmessageContaining(submessage, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentControlBySubmessageContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentControl> findTAssignmentControlBySubmessageContaining(String submessage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentControlBySubmessageContaining", startResult, maxRows, submessage);
		return new LinkedHashSet<TAssignmentControl>(query.getResultList());
	}

	/**
	 * JPQL Query - findTAssignmentControlByTimelimit
	 *
	 */
	@Transactional
	public Set<TAssignmentControl> findTAssignmentControlByTimelimit(Integer timelimit) throws DataAccessException {

		return findTAssignmentControlByTimelimit(timelimit, -1, -1);
	}

	/**
	 * JPQL Query - findTAssignmentControlByTimelimit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentControl> findTAssignmentControlByTimelimit(Integer timelimit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTAssignmentControlByTimelimit", startResult, maxRows, timelimit);
		return new LinkedHashSet<TAssignmentControl>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTAssignmentControls
	 *
	 */
	@Transactional
	public Set<TAssignmentControl> findAllTAssignmentControls() throws DataAccessException {

		return findAllTAssignmentControls(-1, -1);
	}

	/**
	 * JPQL Query - findAllTAssignmentControls
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TAssignmentControl> findAllTAssignmentControls(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTAssignmentControls", startResult, maxRows);
		return new LinkedHashSet<TAssignmentControl>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TAssignmentControl entity) {
		return true;
	}
}
