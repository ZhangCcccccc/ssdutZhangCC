package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.Menu;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Menu entities.
 * 
 */
@Repository("MenuDAO")
@Transactional
public class MenuDAOImpl extends AbstractJpaDao<Menu> implements MenuDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Menu.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new MenuDAOImpl
	 *
	 */
	public MenuDAOImpl() {
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
	 * JPQL Query - findMenuByMemoContaining
	 *
	 */
	@Transactional
	public Set<Menu> findMenuByMemoContaining(String memo) throws DataAccessException {

		return findMenuByMemoContaining(memo, -1, -1);
	}

	/**
	 * JPQL Query - findMenuByMemoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Menu> findMenuByMemoContaining(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMenuByMemoContaining", startResult, maxRows, memo);
		return new LinkedHashSet<Menu>(query.getResultList());
	}

	/**
	 * JPQL Query - findMenuById
	 *
	 */
	@Transactional
	public Menu findMenuById(Integer id) throws DataAccessException {

		return findMenuById(id, -1, -1);
	}

	/**
	 * JPQL Query - findMenuById
	 *
	 */

	@Transactional
	public Menu findMenuById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMenuById", startResult, maxRows, id);
			return (net.xidlims.domain.Menu) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findMenuByMemo
	 *
	 */
	@Transactional
	public Set<Menu> findMenuByMemo(String memo) throws DataAccessException {

		return findMenuByMemo(memo, -1, -1);
	}

	/**
	 * JPQL Query - findMenuByMemo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Menu> findMenuByMemo(String memo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMenuByMemo", startResult, maxRows, memo);
		return new LinkedHashSet<Menu>(query.getResultList());
	}

	/**
	 * JPQL Query - findMenuByPrimaryKey
	 *
	 */
	@Transactional
	public Menu findMenuByPrimaryKey(Integer id) throws DataAccessException {

		return findMenuByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findMenuByPrimaryKey
	 *
	 */

	@Transactional
	public Menu findMenuByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findMenuByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.Menu) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findMenuByName
	 *
	 */
	@Transactional
	public Set<Menu> findMenuByName(String name) throws DataAccessException {

		return findMenuByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findMenuByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Menu> findMenuByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMenuByName", startResult, maxRows, name);
		return new LinkedHashSet<Menu>(query.getResultList());
	}

	/**
	 * JPQL Query - findMenuByNameContaining
	 *
	 */
	@Transactional
	public Set<Menu> findMenuByNameContaining(String name) throws DataAccessException {

		return findMenuByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findMenuByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Menu> findMenuByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findMenuByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<Menu>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllMenus
	 *
	 */
	@Transactional
	public Set<Menu> findAllMenus() throws DataAccessException {

		return findAllMenus(-1, -1);
	}

	/**
	 * JPQL Query - findAllMenus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Menu> findAllMenus(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllMenus", startResult, maxRows);
		return new LinkedHashSet<Menu>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Menu entity) {
		return true;
	}
}
