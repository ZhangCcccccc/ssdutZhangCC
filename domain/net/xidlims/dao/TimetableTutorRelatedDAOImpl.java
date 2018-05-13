package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableTutorRelated;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableTutorRelated entities.
 * 
 */
@Repository("TimetableTutorRelatedDAO")
@Transactional
public class TimetableTutorRelatedDAOImpl extends AbstractJpaDao<TimetableTutorRelated>
		implements TimetableTutorRelatedDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableTutorRelated.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableTutorRelatedDAOImpl
	 *
	 */
	public TimetableTutorRelatedDAOImpl() {
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
	 * JPQL Query - findAllTimetableTutorRelateds
	 *
	 */
	@Transactional
	public Set<TimetableTutorRelated> findAllTimetableTutorRelateds() throws DataAccessException {

		return findAllTimetableTutorRelateds(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableTutorRelateds
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableTutorRelated> findAllTimetableTutorRelateds(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableTutorRelateds", startResult, maxRows);
		return new LinkedHashSet<TimetableTutorRelated>(query.getResultList());
	}

	/**
	 * JPQL Query - findTimetableTutorRelatedByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableTutorRelated findTimetableTutorRelatedByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableTutorRelatedByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableTutorRelatedByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableTutorRelated findTimetableTutorRelatedByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableTutorRelatedByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableTutorRelated) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableTutorRelatedById
	 *
	 */
	@Transactional
	public TimetableTutorRelated findTimetableTutorRelatedById(Integer id) throws DataAccessException {

		return findTimetableTutorRelatedById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableTutorRelatedById
	 *
	 */

	@Transactional
	public TimetableTutorRelated findTimetableTutorRelatedById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableTutorRelatedById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableTutorRelated) query.getSingleResult();
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
	public boolean canBeMerged(TimetableTutorRelated entity) {
		return true;
	}
}
