package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.WkFolder;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage WkFolder entities.
 * 
 */
@Repository("WkFolderDAO")
@Transactional
public class WkFolderDAOImpl extends AbstractJpaDao<WkFolder> implements WkFolderDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { WkFolder.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new WkFolderDAOImpl
	 *
	 */
	public WkFolderDAOImpl() {
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
	 * JPQL Query - findWkFolderByUpdateTime
	 *
	 */
	@Transactional
	public Set<WkFolder> findWkFolderByUpdateTime(java.util.Calendar updateTime) throws DataAccessException {

		return findWkFolderByUpdateTime(updateTime, -1, -1);
	}

	/**
	 * JPQL Query - findWkFolderByUpdateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkFolder> findWkFolderByUpdateTime(java.util.Calendar updateTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkFolderByUpdateTime", startResult, maxRows, updateTime);
		return new LinkedHashSet<WkFolder>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkFolderById
	 *
	 */
	@Transactional
	public WkFolder findWkFolderById(Integer id) throws DataAccessException {

		return findWkFolderById(id, -1, -1);
	}

	/**
	 * JPQL Query - findWkFolderById
	 *
	 */

	@Transactional
	public WkFolder findWkFolderById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWkFolderById", startResult, maxRows, id);
			return (net.xidlims.domain.WkFolder) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findWkFolderByName
	 *
	 */
	@Transactional
	public Set<WkFolder> findWkFolderByName(String name) throws DataAccessException {

		return findWkFolderByName(name, -1, -1);
	}

	/**
	 * JPQL Query - findWkFolderByName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkFolder> findWkFolderByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkFolderByName", startResult, maxRows, name);
		return new LinkedHashSet<WkFolder>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkFolderByCreateTime
	 *
	 */
	@Transactional
	public Set<WkFolder> findWkFolderByCreateTime(java.util.Calendar createTime) throws DataAccessException {

		return findWkFolderByCreateTime(createTime, -1, -1);
	}

	/**
	 * JPQL Query - findWkFolderByCreateTime
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkFolder> findWkFolderByCreateTime(java.util.Calendar createTime, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkFolderByCreateTime", startResult, maxRows, createTime);
		return new LinkedHashSet<WkFolder>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllWkFolders
	 *
	 */
	@Transactional
	public Set<WkFolder> findAllWkFolders() throws DataAccessException {

		return findAllWkFolders(-1, -1);
	}

	/**
	 * JPQL Query - findAllWkFolders
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkFolder> findAllWkFolders(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllWkFolders", startResult, maxRows);
		return new LinkedHashSet<WkFolder>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkFolderByNameContaining
	 *
	 */
	@Transactional
	public Set<WkFolder> findWkFolderByNameContaining(String name) throws DataAccessException {

		return findWkFolderByNameContaining(name, -1, -1);
	}

	/**
	 * JPQL Query - findWkFolderByNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<WkFolder> findWkFolderByNameContaining(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWkFolderByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<WkFolder>(query.getResultList());
	}

	/**
	 * JPQL Query - findWkFolderByPrimaryKey
	 *
	 */
	@Transactional
	public WkFolder findWkFolderByPrimaryKey(Integer id) throws DataAccessException {

		return findWkFolderByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findWkFolderByPrimaryKey
	 *
	 */

	@Transactional
	public WkFolder findWkFolderByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWkFolderByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.WkFolder) query.getSingleResult();
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
	public boolean canBeMerged(WkFolder entity) {
		return true;
	}
}
