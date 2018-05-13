package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetAppDate;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetAppDate entities.
 * 
 */
@Repository("AssetAppDateDAO")
@Transactional
public class AssetAppDateDAOImpl extends AbstractJpaDao<AssetAppDate> implements
		AssetAppDateDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetAppDate.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetAppDateDAOImpl
	 *
	 */
	public AssetAppDateDAOImpl() {
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
	 * JPQL Query - findAllAssetAppDates
	 *
	 */
	@Transactional
	public Set<AssetAppDate> findAllAssetAppDates() throws DataAccessException {

		return findAllAssetAppDates(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetAppDates
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppDate> findAllAssetAppDates(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetAppDates", startResult, maxRows);
		return new LinkedHashSet<AssetAppDate>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppDateById
	 *
	 */
	@Transactional
	public AssetAppDate findAssetAppDateById(Integer id) throws DataAccessException {

		return findAssetAppDateById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppDateById
	 *
	 */

	@Transactional
	public AssetAppDate findAssetAppDateById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetAppDateById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetAppDate) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetAppDateByPrimaryKey
	 *
	 */
	@Transactional
	public AssetAppDate findAssetAppDateByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetAppDateByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppDateByPrimaryKey
	 *
	 */

	@Transactional
	public AssetAppDate findAssetAppDateByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetAppDateByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetAppDate) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetAppDateByStartDate
	 *
	 */
	@Transactional
	public Set<AssetAppDate> findAssetAppDateByStartDate(java.util.Calendar startDate) throws DataAccessException {

		return findAssetAppDateByStartDate(startDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppDateByStartDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppDate> findAssetAppDateByStartDate(java.util.Calendar startDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppDateByStartDate", startResult, maxRows, startDate);
		return new LinkedHashSet<AssetAppDate>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppDateByEndDate
	 *
	 */
	@Transactional
	public Set<AssetAppDate> findAssetAppDateByEndDate(java.util.Calendar endDate) throws DataAccessException {

		return findAssetAppDateByEndDate(endDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppDateByEndDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetAppDate> findAssetAppDateByEndDate(java.util.Calendar endDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppDateByEndDate", startResult, maxRows, endDate);
		return new LinkedHashSet<AssetAppDate>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetAppDate entity) {
		return true;
	}
}
