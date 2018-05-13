package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.TCourseSiteUser;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage TCourseSiteUser entities.
 * 
 */
@Repository("TCourseSiteUserDAO")
@Transactional
public class TCourseSiteUserDAOImpl extends AbstractJpaDao<TCourseSiteUser>
		implements TCourseSiteUserDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { TCourseSiteUser.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlims
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new TCourseSiteUserDAOImpl
	 *
	 */
	public TCourseSiteUserDAOImpl() {
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
	 * JPQL Query - findTCourseSiteUserByPrimaryKey
	 *
	 */
	@Transactional
	public TCourseSiteUser findTCourseSiteUserByPrimaryKey(Integer id) throws DataAccessException {

		return findTCourseSiteUserByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteUserByPrimaryKey
	 *
	 */

	@Transactional
	public TCourseSiteUser findTCourseSiteUserByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteUserByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteUser) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findTCourseSiteUserByPermission
	 *
	 */
	@Transactional
	public Set<TCourseSiteUser> findTCourseSiteUserByPermission(Integer permission) throws DataAccessException {

		return findTCourseSiteUserByPermission(permission, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteUserByPermission
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteUser> findTCourseSiteUserByPermission(Integer permission, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findTCourseSiteUserByPermission", startResult, maxRows, permission);
		return new LinkedHashSet<TCourseSiteUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllTCourseSiteUsers
	 *
	 */
	@Transactional
	public Set<TCourseSiteUser> findAllTCourseSiteUsers() throws DataAccessException {

		return findAllTCourseSiteUsers(-1, -1);
	}

	/**
	 * JPQL Query - findAllTCourseSiteUsers
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<TCourseSiteUser> findAllTCourseSiteUsers(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllTCourseSiteUsers", startResult, maxRows);
		return new LinkedHashSet<TCourseSiteUser>(query.getResultList());
	}

	/**
	 * JPQL Query - findTCourseSiteUserById
	 *
	 */
	@Transactional
	public TCourseSiteUser findTCourseSiteUserById(Integer id) throws DataAccessException {

		return findTCourseSiteUserById(id, -1, -1);
	}

	/**
	 * JPQL Query - findTCourseSiteUserById
	 *
	 */

	@Transactional
	public TCourseSiteUser findTCourseSiteUserById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findTCourseSiteUserById", startResult, maxRows, id);
			return (net.xidlims.domain.TCourseSiteUser) query.getSingleResult();
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
	public boolean canBeMerged(TCourseSiteUser entity) {
		return true;
	}
}
