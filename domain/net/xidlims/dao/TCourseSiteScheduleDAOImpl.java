package net.xidlims.dao;

import net.xidlims.domain.TCourseSiteSchedule;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TCourseSiteSchedule entities.
 * 
 */
@Repository("TCourseSiteScheduleDAO")
@Transactional
public class TCourseSiteScheduleDAOImpl extends AbstractJpaDao<TCourseSiteSchedule>
		implements TCourseSiteScheduleDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TCourseSiteSchedule.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TCourseSiteScheduleDAOImpl
	 *
	 */
	public TCourseSiteScheduleDAOImpl() {
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
	 * JPQL Query - findTCourseSiteScheduleByContentContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByContentContaining(String content) throws DataAccessException {

		return findTCourseSiteScheduleByContentContaining(content, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByContentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByContentContaining(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteScheduleByContentContaining", startResult, maxRows, content);
		return new LinkedHashSet<TCourseSiteSchedule>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTCourseSiteSchedules
	 *
	 */
	@Transactional
	public Set<TCourseSiteSchedule> findAllTCourseSiteSchedules() throws DataAccessException {

		return findAllTCourseSiteSchedules(-1, -1);
	}

	/**
	 * JPQL Query - findAllTCourseSiteSchedules
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteSchedule> findAllTCourseSiteSchedules(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTCourseSiteSchedules", startResult, maxRows);
		return new LinkedHashSet<TCourseSiteSchedule>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByPlace
	 *
	 */
	@Transactional
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByPlace(String place) throws DataAccessException {

		return findTCourseSiteScheduleByPlace(place, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByPlace
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByPlace(String place, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteScheduleByPlace", startResult, maxRows, place);
		return new LinkedHashSet<TCourseSiteSchedule>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleById
	 *
	 */
	@Transactional
	public TCourseSiteSchedule findTCourseSiteScheduleById(Integer id) throws DataAccessException {

		return findTCourseSiteScheduleById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleById
	 *
	 */

	@Transactional
	public TCourseSiteSchedule findTCourseSiteScheduleById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteScheduleById", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteSchedule) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByPrimaryKey
	 *
	 */
	@Transactional
	public TCourseSiteSchedule findTCourseSiteScheduleByPrimaryKey(Integer id) throws DataAccessException {

		return findTCourseSiteScheduleByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByPrimaryKey
	 *
	 */

	@Transactional
	public TCourseSiteSchedule findTCourseSiteScheduleByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteScheduleByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteSchedule) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByPlaceContaining
	 *
	 */
	@Transactional
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByPlaceContaining(String place) throws DataAccessException {

		return findTCourseSiteScheduleByPlaceContaining(place, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByPlaceContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByPlaceContaining(String place, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteScheduleByPlaceContaining", startResult, maxRows, place);
		return new LinkedHashSet<TCourseSiteSchedule>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByCreateTime
	 *
	 */
	@Transactional
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findTCourseSiteScheduleByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteScheduleByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<TCourseSiteSchedule>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByContent
	 *
	 */
	@Transactional
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByContent(String content) throws DataAccessException {

		return findTCourseSiteScheduleByContent(content, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteScheduleByContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteSchedule> findTCourseSiteScheduleByContent(String content, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteScheduleByContent", startResult, maxRows, content);
		return new LinkedHashSet<TCourseSiteSchedule>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TCourseSiteSchedule entity) {
		return true;
	}
}
