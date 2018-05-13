package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableLabRelated;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableLabRelated entities.
 * 
 */
@Repository("TimetableLabRelatedDAO")
@Transactional
public class TimetableLabRelatedDAOImpl extends AbstractJpaDao<TimetableLabRelated>
		implements TimetableLabRelatedDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableLabRelated.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableLabRelatedDAOImpl
	 *
	 */
	public TimetableLabRelatedDAOImpl() {
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
	 * JPQL Query - findTimetableLabRelatedById
	 *
	 */
	@Transactional
	public TimetableLabRelated findTimetableLabRelatedById(Integer id) throws DataAccessException {

		return findTimetableLabRelatedById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableLabRelatedById
	 *
	 */

	@Transactional
	public TimetableLabRelated findTimetableLabRelatedById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableLabRelatedById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableLabRelated) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTimetableLabRelateds
	 *
	 */
	@Transactional
	public Set<TimetableLabRelated> findAllTimetableLabRelateds() throws DataAccessException {

		return findAllTimetableLabRelateds(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableLabRelateds
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableLabRelated> findAllTimetableLabRelateds(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableLabRelateds", startResult, maxRows);
		return new LinkedHashSet<TimetableLabRelated>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableLabRelatedByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableLabRelated findTimetableLabRelatedByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableLabRelatedByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableLabRelatedByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableLabRelated findTimetableLabRelatedByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableLabRelatedByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableLabRelated) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TimetableLabRelated entity) {
		return true;
	}
}
