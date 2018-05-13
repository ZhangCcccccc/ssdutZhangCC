package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetOpenLog;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetOpenLog entities.
 * 
 */
@Repository("AssetOpenLogDAO")
@Transactional
public class AssetOpenLogDAOImpl extends AbstractJpaDao<AssetOpenLog> implements
		AssetOpenLogDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetOpenLog.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetOpenLogDAOImpl
	 *
	 */
	public AssetOpenLogDAOImpl() {
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
	 * JPQL Query - findAssetOpenLogByStatus
	 *
	 */
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByStatus(Integer status) throws DataAccessException {

		return findAssetOpenLogByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findAssetOpenLogByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByStatus(Integer status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetOpenLogByStatus", startResult, maxRows, status);
		return new LinkedHashSet<AssetOpenLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetOpenLogByMemContaining
	 *
	 */
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByMemContaining(String mem) throws DataAccessException {

		return findAssetOpenLogByMemContaining(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetOpenLogByMemContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByMemContaining(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetOpenLogByMemContaining", startResult, maxRows, mem);
		return new LinkedHashSet<AssetOpenLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetOpenLogByMem
	 *
	 */
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByMem(String mem) throws DataAccessException {

		return findAssetOpenLogByMem(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetOpenLogByMem
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByMem(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetOpenLogByMem", startResult, maxRows, mem);
		return new LinkedHashSet<AssetOpenLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetOpenLogByCreateDate
	 *
	 */
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByCreateDate(java.util.Calendar createDate) throws DataAccessException {

		return findAssetOpenLogByCreateDate(createDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetOpenLogByCreateDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByCreateDate(java.util.Calendar createDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetOpenLogByCreateDate", startResult, maxRows, createDate);
		return new LinkedHashSet<AssetOpenLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetOpenLogByCreateUser
	 *
	 */
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByCreateUser(String createUser) throws DataAccessException {

		return findAssetOpenLogByCreateUser(createUser, -1, -1);
	}

	/**
	 * JPQL Query - findAssetOpenLogByCreateUser
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByCreateUser(String createUser, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetOpenLogByCreateUser", startResult, maxRows, createUser);
		return new LinkedHashSet<AssetOpenLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetOpenLogByOpenDate
	 *
	 */
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByOpenDate(java.util.Calendar openDate) throws DataAccessException {

		return findAssetOpenLogByOpenDate(openDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetOpenLogByOpenDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByOpenDate(java.util.Calendar openDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetOpenLogByOpenDate", startResult, maxRows, openDate);
		return new LinkedHashSet<AssetOpenLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetOpenLogByPrimaryKey
	 *
	 */
	@Transactional
	public AssetOpenLog findAssetOpenLogByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetOpenLogByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetOpenLogByPrimaryKey
	 *
	 */

	@Transactional
	public AssetOpenLog findAssetOpenLogByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetOpenLogByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetOpenLog) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetOpenLogById
	 *
	 */
	@Transactional
	public AssetOpenLog findAssetOpenLogById(Integer id) throws DataAccessException {

		return findAssetOpenLogById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetOpenLogById
	 *
	 */

	@Transactional
	public AssetOpenLog findAssetOpenLogById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetOpenLogById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetOpenLog) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllAssetOpenLogs
	 *
	 */
	@Transactional
	public Set<AssetOpenLog> findAllAssetOpenLogs() throws DataAccessException {

		return findAllAssetOpenLogs(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetOpenLogs
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetOpenLog> findAllAssetOpenLogs(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetOpenLogs", startResult, maxRows);
		return new LinkedHashSet<AssetOpenLog>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetOpenLogByCreateUserContaining
	 *
	 */
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByCreateUserContaining(String createUser) throws DataAccessException {

		return findAssetOpenLogByCreateUserContaining(createUser, -1, -1);
	}

	/**
	 * JPQL Query - findAssetOpenLogByCreateUserContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetOpenLog> findAssetOpenLogByCreateUserContaining(String createUser, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetOpenLogByCreateUserContaining", startResult, maxRows, createUser);
		return new LinkedHashSet<AssetOpenLog>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetOpenLog entity) {
		return true;
	}
}
