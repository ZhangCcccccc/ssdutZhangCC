package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AuthorityMenu;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AuthorityMenu entities.
 * 
 */
@Repository("AuthorityMenuDAO")
@Transactional
public class AuthorityMenuDAOImpl extends AbstractJpaDao<AuthorityMenu>
		implements AuthorityMenuDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AuthorityMenu.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AuthorityMenuDAOImpl
	 *
	 */
	public AuthorityMenuDAOImpl() {
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
	 * JPQL Query - findAllAuthorityMenus
	 *
	 */
	@Transactional
	public Set<AuthorityMenu> findAllAuthorityMenus() throws DataAccessException {

		return findAllAuthorityMenus(-1, -1);
	}

	/**
	 * JPQL Query - findAllAuthorityMenus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AuthorityMenu> findAllAuthorityMenus(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAuthorityMenus", startResult, maxRows);
		return new LinkedHashSet<AuthorityMenu>(query.getResultList());
	}

	/**
	 * JPQL Query - findAuthorityMenuById
	 *
	 */
	@Transactional
	public AuthorityMenu findAuthorityMenuById(Integer id) throws DataAccessException {

		return findAuthorityMenuById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAuthorityMenuById
	 *
	 */

	@Transactional
	public AuthorityMenu findAuthorityMenuById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAuthorityMenuById", startResult, maxRows, id);
			return (net.xidlims.domain.AuthorityMenu) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAuthorityMenuByPrimaryKey
	 *
	 */
	@Transactional
	public AuthorityMenu findAuthorityMenuByPrimaryKey(Integer id) throws DataAccessException {

		return findAuthorityMenuByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAuthorityMenuByPrimaryKey
	 *
	 */

	@Transactional
	public AuthorityMenu findAuthorityMenuByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAuthorityMenuByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AuthorityMenu) query.getSingleResult();
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
	public boolean canBeMerged(AuthorityMenu entity) {
		return true;
	}
}
