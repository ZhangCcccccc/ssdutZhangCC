package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.LabRoomLimitTime;

import org.skyway.spring.util.dao.AbstractJpaDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage LabRoomLimitTime entities.
 * 
 */
@Repository("LabRoomLimitTimeDAO")
@Transactional
public class LabRoomLimitTimeDAOImpl extends AbstractJpaDao<LabRoomLimitTime>
		implements LabRoomLimitTimeDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { LabRoomLimitTime.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new LabRoomLimitTimeDAOImpl
	 *
	 */
	public LabRoomLimitTimeDAOImpl() {
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
	 * JPQL Query - findLabRoomLimitTimeByTerm
	 *
	 */
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByTerm(Integer term) throws DataAccessException {

		return findLabRoomLimitTimeByTerm(term, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByTerm
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByTerm(Integer term, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLimitTimeByTerm", startResult, maxRows, term);
		return new LinkedHashSet<LabRoomLimitTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByPrimaryKey
	 *
	 */
	@Transactional
	public LabRoomLimitTime findLabRoomLimitTimeByPrimaryKey(Integer id) throws DataAccessException {

		return findLabRoomLimitTimeByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByPrimaryKey
	 *
	 */

	@Transactional
	public LabRoomLimitTime findLabRoomLimitTimeByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomLimitTimeByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomLimitTime) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByEndweek
	 *
	 */
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByEndweek(Integer endweek) throws DataAccessException {

		return findLabRoomLimitTimeByEndweek(endweek, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByEndweek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByEndweek(Integer endweek, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLimitTimeByEndweek", startResult, maxRows, endweek);
		return new LinkedHashSet<LabRoomLimitTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllLabRoomLimitTimes
	 *
	 */
	@Transactional
	public Set<LabRoomLimitTime> findAllLabRoomLimitTimes() throws DataAccessException {

		return findAllLabRoomLimitTimes(-1, -1);
	}

	/**
	 * JPQL Query - findAllLabRoomLimitTimes
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLimitTime> findAllLabRoomLimitTimes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllLabRoomLimitTimes", startResult, maxRows);
		return new LinkedHashSet<LabRoomLimitTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByStartweek
	 *
	 */
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByStartweek(Integer startweek) throws DataAccessException {

		return findLabRoomLimitTimeByStartweek(startweek, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByStartweek
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByStartweek(Integer startweek, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLimitTimeByStartweek", startResult, maxRows, startweek);
		return new LinkedHashSet<LabRoomLimitTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByLabId
	 *
	 */
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByLabId(Integer labId) throws DataAccessException {

		return findLabRoomLimitTimeByLabId(labId, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByLabId
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByLabId(Integer labId, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLimitTimeByLabId", startResult, maxRows, labId);
		return new LinkedHashSet<LabRoomLimitTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByInfoContaining
	 *
	 */
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByInfoContaining(String info) throws DataAccessException {

		return findLabRoomLimitTimeByInfoContaining(info, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByInfoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByInfoContaining(String info, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLimitTimeByInfoContaining", startResult, maxRows, info);
		return new LinkedHashSet<LabRoomLimitTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByInfo
	 *
	 */
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByInfo(String info) throws DataAccessException {

		return findLabRoomLimitTimeByInfo(info, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByInfo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByInfo(String info, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLimitTimeByInfo", startResult, maxRows, info);
		return new LinkedHashSet<LabRoomLimitTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByWeekday
	 *
	 */
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByWeekday(Integer weekday) throws DataAccessException {

		return findLabRoomLimitTimeByWeekday(weekday, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByWeekday
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByWeekday(Integer weekday, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLimitTimeByWeekday", startResult, maxRows, weekday);
		return new LinkedHashSet<LabRoomLimitTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeById
	 *
	 */
	@Transactional
	public LabRoomLimitTime findLabRoomLimitTimeById(Integer id) throws DataAccessException {

		return findLabRoomLimitTimeById(id, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeById
	 *
	 */

	@Transactional
	public LabRoomLimitTime findLabRoomLimitTimeById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLabRoomLimitTimeById", startResult, maxRows, id);
			return (net.xidlims.domain.LabRoomLimitTime) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByStartclass
	 *
	 */
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByStartclass(Integer startclass) throws DataAccessException {

		return findLabRoomLimitTimeByStartclass(startclass, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByStartclass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByStartclass(Integer startclass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLimitTimeByStartclass", startResult, maxRows, startclass);
		return new LinkedHashSet<LabRoomLimitTime>(query.getResultList());
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByEndclass
	 *
	 */
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByEndclass(Integer endclass) throws DataAccessException {

		return findLabRoomLimitTimeByEndclass(endclass, -1, -1);
	}

	/**
	 * JPQL Query - findLabRoomLimitTimeByEndclass
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<LabRoomLimitTime> findLabRoomLimitTimeByEndclass(Integer endclass, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLabRoomLimitTimeByEndclass", startResult, maxRows, endclass);
		return new LinkedHashSet<LabRoomLimitTime>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(LabRoomLimitTime entity) {
		return true;
	}
}
