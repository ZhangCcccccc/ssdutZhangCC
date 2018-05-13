package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetApp;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetApp entities.
 * 
 */
@Repository("AssetAppDAO")
@Transactional
public class AssetAppDAOImpl extends AbstractJpaDao<AssetApp> implements
		AssetAppDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetApp.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetAppDAOImpl
	 *
	 */
	public AssetAppDAOImpl() {
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
	 * JPQL Query - findAssetAppBySaveSubmit
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppBySaveSubmit(Integer saveSubmit) throws DataAccessException {

		return findAssetAppBySaveSubmit(saveSubmit, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppBySaveSubmit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppBySaveSubmit(Integer saveSubmit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppBySaveSubmit", startResult, maxRows, saveSubmit);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByMem
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByMem(String mem) throws DataAccessException {

		return findAssetAppByMem(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByMem
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByMem(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByMem", startResult, maxRows, mem);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByAppDateBefore
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByAppDateBefore(java.util.Calendar appDate) throws DataAccessException {

		return findAssetAppByAppDateBefore(appDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByAppDateBefore
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByAppDateBefore(java.util.Calendar appDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByAppDateBefore", startResult, maxRows, appDate);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByAppNo
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByAppNo(String appNo) throws DataAccessException {

		return findAssetAppByAppNo(appNo, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByAppNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByAppNo(String appNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByAppNo", startResult, maxRows, appNo);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByAppDateAfter
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByAppDateAfter(java.util.Calendar appDate) throws DataAccessException {

		return findAssetAppByAppDateAfter(appDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByAppDateAfter
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByAppDateAfter(java.util.Calendar appDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByAppDateAfter", startResult, maxRows, appDate);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByProjectNameContaining
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByProjectNameContaining(String projectName) throws DataAccessException {

		return findAssetAppByProjectNameContaining(projectName, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByProjectNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByProjectNameContaining(String projectName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByProjectNameContaining", startResult, maxRows, projectName);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppById
	 *
	 */
	@Transactional
	public AssetApp findAssetAppById(Integer id) throws DataAccessException {

		return findAssetAppById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppById
	 *
	 */

	@Transactional
	public AssetApp findAssetAppById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetAppById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetApp) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetAppByAppDate
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByAppDate(java.util.Calendar appDate) throws DataAccessException {

		return findAssetAppByAppDate(appDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByAppDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByAppDate(java.util.Calendar appDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByAppDate", startResult, maxRows, appDate);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByProjectName
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByProjectName(String projectName) throws DataAccessException {

		return findAssetAppByProjectName(projectName, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByProjectName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByProjectName(String projectName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByProjectName", startResult, maxRows, projectName);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByPrimaryKey
	 *
	 */
	@Transactional
	public AssetApp findAssetAppByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetAppByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByPrimaryKey
	 *
	 */

	@Transactional
	public AssetApp findAssetAppByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetAppByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetApp) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAllAssetApps
	 *
	 */
	@Transactional
	public Set<AssetApp> findAllAssetApps() throws DataAccessException {

		return findAllAssetApps(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetApps
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAllAssetApps(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetApps", startResult, maxRows);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByAppNoContaining
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByAppNoContaining(String appNo) throws DataAccessException {

		return findAssetAppByAppNoContaining(appNo, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByAppNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByAppNoContaining(String appNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByAppNoContaining", startResult, maxRows, appNo);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByAssetStatu
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByAssetStatu(Integer assetStatu) throws DataAccessException {

		return findAssetAppByAssetStatu(assetStatu, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByAssetStatu
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByAssetStatu(Integer assetStatu, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByAssetStatu", startResult, maxRows, assetStatu);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByAssetAuditStatus
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByAssetAuditStatus(Integer assetAuditStatus) throws DataAccessException {

		return findAssetAppByAssetAuditStatus(assetAuditStatus, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByAssetAuditStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByAssetAuditStatus(Integer assetAuditStatus, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByAssetAuditStatus", startResult, maxRows, assetAuditStatus);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetAppByAppType
	 *
	 */
	@Transactional
	public Set<AssetApp> findAssetAppByAppType(Integer appType) throws DataAccessException {

		return findAssetAppByAppType(appType, -1, -1);
	}

	/**
	 * JPQL Query - findAssetAppByAppType
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetApp> findAssetAppByAppType(Integer appType, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetAppByAppType", startResult, maxRows, appType);
		return new LinkedHashSet<AssetApp>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetApp entity) {
		return true;
	}
}
