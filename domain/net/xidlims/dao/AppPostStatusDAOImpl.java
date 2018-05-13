package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AppPostStatus;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AppPostStatus entities.
 * 
 */
@Repository("AppPostStatusDAO")
@Transactional
public class AppPostStatusDAOImpl extends AbstractJpaDao<AppPostStatus>
		implements AppPostStatusDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AppPostStatus.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AppPostStatusDAOImpl
	 *
	 */
	public AppPostStatusDAOImpl() {
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
	 * JPQL Query - findAppPostStatusById
	 *
	 */
	@Transactional
	public AppPostStatus findAppPostStatusById(Integer id) throws DataAccessException {

		return findAppPostStatusById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostStatusById
	 *
	 */

	@Transactional
	public AppPostStatus findAppPostStatusById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppPostStatusById", startResult, maxRows, id);
			return (net.xidlims.domain.AppPostStatus) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppPostStatusByPrimaryKey
	 *
	 */
	@Transactional
	public AppPostStatus findAppPostStatusByPrimaryKey(Integer id) throws DataAccessException {

		return findAppPostStatusByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostStatusByPrimaryKey
	 *
	 */

	@Transactional
	public AppPostStatus findAppPostStatusByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppPostStatusByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AppPostStatus) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppPostStatusByType
	 *
	 */
	@Transactional
	public Set<AppPostStatus> findAppPostStatusByType(Integer type) throws DataAccessException {

		return findAppPostStatusByType(type, -1, -1);
	}

	/**
	 * JPQL Query - findAppPostStatusByType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostStatus> findAppPostStatusByType(Integer type, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppPostStatusByType", startResult, maxRows, type);
		return new LinkedHashSet<AppPostStatus>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAppPostStatuss
	 *
	 */
	@Transactional
	public Set<AppPostStatus> findAllAppPostStatuss() throws DataAccessException {

		return findAllAppPostStatuss(-1, -1);
	}

	/**
	 * JPQL Query - findAllAppPostStatuss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppPostStatus> findAllAppPostStatuss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAppPostStatuss", startResult, maxRows);
		return new LinkedHashSet<AppPostStatus>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AppPostStatus entity) {
		return true;
	}
}
