package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetReceiveAllocation;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetReceiveAllocation entities.
 * 
 */
@Repository("AssetReceiveAllocationDAO")
@Transactional
public class AssetReceiveAllocationDAOImpl extends AbstractJpaDao<AssetReceiveAllocation>
		implements AssetReceiveAllocationDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetReceiveAllocation.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetReceiveAllocationDAOImpl
	 *
	 */
	public AssetReceiveAllocationDAOImpl() {
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
	 * JPQL Query - findAssetReceiveAllocationByMemContaining
	 *
	 */
	@Transactional
	public Set<AssetReceiveAllocation> findAssetReceiveAllocationByMemContaining(String mem) throws DataAccessException {

		return findAssetReceiveAllocationByMemContaining(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAllocationByMemContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveAllocation> findAssetReceiveAllocationByMemContaining(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveAllocationByMemContaining", startResult, maxRows, mem);
		return new LinkedHashSet<AssetReceiveAllocation>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveAllocationById
	 *
	 */
	@Transactional
	public AssetReceiveAllocation findAssetReceiveAllocationById(Integer id) throws DataAccessException {

		return findAssetReceiveAllocationById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAllocationById
	 *
	 */

	@Transactional
	public AssetReceiveAllocation findAssetReceiveAllocationById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetReceiveAllocationById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetReceiveAllocation) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetReceiveAllocationByPrimaryKey
	 *
	 */
	@Transactional
	public AssetReceiveAllocation findAssetReceiveAllocationByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetReceiveAllocationByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAllocationByPrimaryKey
	 *
	 */

	@Transactional
	public AssetReceiveAllocation findAssetReceiveAllocationByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetReceiveAllocationByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetReceiveAllocation) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllAssetReceiveAllocations
	 *
	 */
	@Transactional
	public Set<AssetReceiveAllocation> findAllAssetReceiveAllocations() throws DataAccessException {

		return findAllAssetReceiveAllocations(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetReceiveAllocations
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveAllocation> findAllAssetReceiveAllocations(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetReceiveAllocations", startResult, maxRows);
		return new LinkedHashSet<AssetReceiveAllocation>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveAllocationByMem
	 *
	 */
	@Transactional
	public Set<AssetReceiveAllocation> findAssetReceiveAllocationByMem(String mem) throws DataAccessException {

		return findAssetReceiveAllocationByMem(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveAllocationByMem
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceiveAllocation> findAssetReceiveAllocationByMem(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveAllocationByMem", startResult, maxRows, mem);
		return new LinkedHashSet<AssetReceiveAllocation>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetReceiveAllocation entity) {
		return true;
	}
}
