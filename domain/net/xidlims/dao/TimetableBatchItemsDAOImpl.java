package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableBatchItems;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableBatchItems entities.
 * 
 */
@Repository("TimetableBatchItemsDAO")
@Transactional
public class TimetableBatchItemsDAOImpl extends AbstractJpaDao<TimetableBatchItems>
		implements TimetableBatchItemsDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableBatchItems.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableBatchItemsDAOImpl
	 *
	 */
	public TimetableBatchItemsDAOImpl() {
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
	 * JPQL Query - findTimetableBatchItemsById
	 *
	 */
	@Transactional
	public TimetableBatchItems findTimetableBatchItemsById(Integer id) throws DataAccessException {

		return findTimetableBatchItemsById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchItemsById
	 *
	 */

	@Transactional
	public TimetableBatchItems findTimetableBatchItemsById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableBatchItemsById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableBatchItems) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableBatchItemsByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableBatchItems findTimetableBatchItemsByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableBatchItemsByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableBatchItemsByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableBatchItems findTimetableBatchItemsByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableBatchItemsByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableBatchItems) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTimetableBatchItemss
	 *
	 */
	@Transactional
	public Set<TimetableBatchItems> findAllTimetableBatchItemss() throws DataAccessException {

		return findAllTimetableBatchItemss(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableBatchItemss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableBatchItems> findAllTimetableBatchItemss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableBatchItemss", startResult, maxRows);
		return new LinkedHashSet<TimetableBatchItems>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TimetableBatchItems entity) {
		return true;
	}
}
