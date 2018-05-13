package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AppGroup;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AppGroup entities.
 * 
 */
@Repository("AppGroupDAO")
@Transactional
public class AppGroupDAOImpl extends AbstractJpaDao<AppGroup> implements
		AppGroupDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AppGroup.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AppGroupDAOImpl
	 *
	 */
	public AppGroupDAOImpl() {
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
	 * JPQL Query - findAppGroupById
	 *
	 */
	@Transactional
	public AppGroup findAppGroupById(Integer id) throws DataAccessException {

		return findAppGroupById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppGroupById
	 *
	 */

	@Transactional
	public AppGroup findAppGroupById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppGroupById", startResult, maxRows, id);
			return (net.xidlims.domain.AppGroup) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppGroupByNameContaining
	 *
	 */
	@Transactional
	public Set<AppGroup> findAppGroupByNameContaining(String name) throws DataAccessException {

		return findAppGroupByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findAppGroupByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppGroup> findAppGroupByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppGroupByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<AppGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAppGroups
	 *
	 */
	@Transactional
	public Set<AppGroup> findAllAppGroups() throws DataAccessException {

		return findAllAppGroups(-1, -1);
	}

	/**
	 * JPQL Query - findAllAppGroups
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppGroup> findAllAppGroups(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAppGroups", startResult, maxRows);
		return new LinkedHashSet<AppGroup>(query.getResultList());
	}

	/**
	 * JPQL Query - findAppGroupByPrimaryKey
	 *
	 */
	@Transactional
	public AppGroup findAppGroupByPrimaryKey(Integer id) throws DataAccessException {

		return findAppGroupByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAppGroupByPrimaryKey
	 *
	 */

	@Transactional
	public AppGroup findAppGroupByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAppGroupByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AppGroup) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAppGroupByName
	 *
	 */
	@Transactional
	public Set<AppGroup> findAppGroupByName(String name) throws DataAccessException {

		return findAppGroupByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findAppGroupByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AppGroup> findAppGroupByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAppGroupByName", startResult, maxRows, name);
		return new LinkedHashSet<AppGroup>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AppGroup entity) {
		return true;
	}
}
