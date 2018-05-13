package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.MLabConstructionProjectUser;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage MLabConstructionProjectUser entities.
 * 
 */
@Repository("MLabConstructionProjectUserDAO")
@Transactional
public class MLabConstructionProjectUserDAOImpl extends AbstractJpaDao<MLabConstructionProjectUser>
		implements MLabConstructionProjectUserDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { MLabConstructionProjectUser.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new MLabConstructionProjectUserDAOImpl
	 *
	 */
	public MLabConstructionProjectUserDAOImpl() {
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
	 * JPQL Query - findMLabConstructionProjectUserById
	 *
	 */
	@Transactional
	public MLabConstructionProjectUser findMLabConstructionProjectUserById(Integer id) throws DataAccessException {

		return findMLabConstructionProjectUserById(id, -1, -1);
	}

	/**
	 * JPQL Query - findMLabConstructionProjectUserById
	 *
	 */

	@Transactional
	public MLabConstructionProjectUser findMLabConstructionProjectUserById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMLabConstructionProjectUserById", startResult, maxRows, id);
			return (net.xidlims.domain.MLabConstructionProjectUser) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findMLabConstructionProjectUserByPrimaryKey
	 *
	 */
	@Transactional
	public MLabConstructionProjectUser findMLabConstructionProjectUserByPrimaryKey(Integer id) throws DataAccessException {

		return findMLabConstructionProjectUserByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findMLabConstructionProjectUserByPrimaryKey
	 *
	 */

	@Transactional
	public MLabConstructionProjectUser findMLabConstructionProjectUserByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMLabConstructionProjectUserByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.MLabConstructionProjectUser) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllMLabConstructionProjectUsers
	 *
	 */
	@Transactional
	public Set<MLabConstructionProjectUser> findAllMLabConstructionProjectUsers() throws DataAccessException {

		return findAllMLabConstructionProjectUsers(-1, -1);
	}

	/**
	 * JPQL Query - findAllMLabConstructionProjectUsers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<MLabConstructionProjectUser> findAllMLabConstructionProjectUsers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllMLabConstructionProjectUsers", startResult, maxRows);
		return new LinkedHashSet<MLabConstructionProjectUser>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(MLabConstructionProjectUser entity) {
		return true;
	}
}
