package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TimetableTeacherRelated;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TimetableTeacherRelated entities.
 * 
 */
@Repository("TimetableTeacherRelatedDAO")
@Transactional
public class TimetableTeacherRelatedDAOImpl extends AbstractJpaDao<TimetableTeacherRelated>
		implements TimetableTeacherRelatedDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TimetableTeacherRelated.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TimetableTeacherRelatedDAOImpl
	 *
	 */
	public TimetableTeacherRelatedDAOImpl() {
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
	 * JPQL Query - findTimetableTeacherRelatedByPrimaryKey
	 *
	 */
	@Transactional
	public TimetableTeacherRelated findTimetableTeacherRelatedByPrimaryKey(Integer id) throws DataAccessException {

		return findTimetableTeacherRelatedByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableTeacherRelatedByPrimaryKey
	 *
	 */

	@Transactional
	public TimetableTeacherRelated findTimetableTeacherRelatedByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableTeacherRelatedByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableTeacherRelated) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTimetableTeacherRelatedById
	 *
	 */
	@Transactional
	public TimetableTeacherRelated findTimetableTeacherRelatedById(Integer id) throws DataAccessException {

		return findTimetableTeacherRelatedById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTimetableTeacherRelatedById
	 *
	 */

	@Transactional
	public TimetableTeacherRelated findTimetableTeacherRelatedById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTimetableTeacherRelatedById", startResult, maxRows, id);
			return (net.xidlims.domain.TimetableTeacherRelated) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllTimetableTeacherRelateds
	 *
	 */
	@Transactional
	public Set<TimetableTeacherRelated> findAllTimetableTeacherRelateds() throws DataAccessException {

		return findAllTimetableTeacherRelateds(-1, -1);
	}

	/**
	 * JPQL Query - findAllTimetableTeacherRelateds
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TimetableTeacherRelated> findAllTimetableTeacherRelateds(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTimetableTeacherRelateds", startResult, maxRows);
		return new LinkedHashSet<TimetableTeacherRelated>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(TimetableTeacherRelated entity) {
		return true;
	}
}
