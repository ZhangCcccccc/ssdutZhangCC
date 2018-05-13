package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableItemRelated;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableItemRelated entities.
 * 
 */
@Repository("TimetableItemRelatedDAO")
@Transactional
public class TimetableItemRelatedDAOImpl extends AbstractJpaDao<TimetableItemRelated>
		implements TimetableItemRelatedDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableItemRelated.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableItemRelatedDAOImpl
	 *
	 */
	public TimetableItemRelatedDAOImpl() {
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
	 * JPQL Query - findTimetableItemRelatedById
	 *
	 */
	@Transactional
	public TimetableItemRelated findTimetableItemRelatedById(Integer id) throws DataAccessException {

		return findTimetableItemRelatedById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableItemRelatedById
	 *
	 */

	@Transactional
	public TimetableItemRelated findTimetableItemRelatedById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableItemRelatedById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableItemRelated) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableItemRelatedByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableItemRelated findTimetableItemRelatedByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableItemRelatedByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableItemRelatedByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableItemRelated findTimetableItemRelatedByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableItemRelatedByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableItemRelated) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTimetableItemRelateds
	 *
	 */
	@Transactional
	public Set<TimetableItemRelated> findAllTimetableItemRelateds() throws DataAccessException {

		return findAllTimetableItemRelateds(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableItemRelateds
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableItemRelated> findAllTimetableItemRelateds(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableItemRelateds", startResult, maxRows);
		return new LinkedHashSet<TimetableItemRelated>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TimetableItemRelated entity) {
		return true;
	}
}
