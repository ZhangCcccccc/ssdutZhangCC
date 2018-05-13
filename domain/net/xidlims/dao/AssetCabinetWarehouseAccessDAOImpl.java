package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetCabinetWarehouseAccess;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetCabinetWarehouseAccess entities.
 * 
 */
@Repository("AssetCabinetWarehouseAccessDAO")
@Transactional
public class AssetCabinetWarehouseAccessDAOImpl extends AbstractJpaDao<AssetCabinetWarehouseAccess>
		implements AssetCabinetWarehouseAccessDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetCabinetWarehouseAccess.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetCabinetWarehouseAccessDAOImpl
	 *
	 */
	public AssetCabinetWarehouseAccessDAOImpl() {
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
	 * JPQL Query - findAssetCabinetWarehouseAccessById
	 *
	 */
	@Transactional
	public AssetCabinetWarehouseAccess findAssetCabinetWarehouseAccessById(Integer id) throws DataAccessException {

		return findAssetCabinetWarehouseAccessById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessById
	 *
	 */

	@Transactional
	public AssetCabinetWarehouseAccess findAssetCabinetWarehouseAccessById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetCabinetWarehouseAccessById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetCabinetWarehouseAccess) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByMemContaining
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByMemContaining(String mem) throws DataAccessException {

		return findAssetCabinetWarehouseAccessByMemContaining(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByMemContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByMemContaining(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetCabinetWarehouseAccessByMemContaining", startResult, maxRows, mem);
		return new LinkedHashSet<AssetCabinetWarehouseAccess>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByPrimaryKey
	 *
	 */
	@Transactional
	public AssetCabinetWarehouseAccess findAssetCabinetWarehouseAccessByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetCabinetWarehouseAccessByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByPrimaryKey
	 *
	 */

	@Transactional
	public AssetCabinetWarehouseAccess findAssetCabinetWarehouseAccessByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetCabinetWarehouseAccessByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetCabinetWarehouseAccess) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByCreateDate
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByCreateDate(java.util.Calendar createDate) throws DataAccessException {

		return findAssetCabinetWarehouseAccessByCreateDate(createDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByCreateDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByCreateDate(java.util.Calendar createDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetCabinetWarehouseAccessByCreateDate", startResult, maxRows, createDate);
		return new LinkedHashSet<AssetCabinetWarehouseAccess>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAssetCabinetWarehouseAccesss
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAllAssetCabinetWarehouseAccesss() throws DataAccessException {

		return findAllAssetCabinetWarehouseAccesss(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetCabinetWarehouseAccesss
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAllAssetCabinetWarehouseAccesss(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetCabinetWarehouseAccesss", startResult, maxRows);
		return new LinkedHashSet<AssetCabinetWarehouseAccess>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByMem
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByMem(String mem) throws DataAccessException {

		return findAssetCabinetWarehouseAccessByMem(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByMem
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByMem(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetCabinetWarehouseAccessByMem", startResult, maxRows, mem);
		return new LinkedHashSet<AssetCabinetWarehouseAccess>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByStatus
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByStatus(Integer status) throws DataAccessException {

		return findAssetCabinetWarehouseAccessByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByStatus(Integer status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetCabinetWarehouseAccessByStatus", startResult, maxRows, status);
		return new LinkedHashSet<AssetCabinetWarehouseAccess>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByCabinetQuantity
	 *
	 */
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByCabinetQuantity(java.math.BigDecimal cabinetQuantity) throws DataAccessException {

		return findAssetCabinetWarehouseAccessByCabinetQuantity(cabinetQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findAssetCabinetWarehouseAccessByCabinetQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetCabinetWarehouseAccess> findAssetCabinetWarehouseAccessByCabinetQuantity(java.math.BigDecimal cabinetQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetCabinetWarehouseAccessByCabinetQuantity", startResult, maxRows, cabinetQuantity);
		return new LinkedHashSet<AssetCabinetWarehouseAccess>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetCabinetWarehouseAccess entity) {
		return true;
	}
}
