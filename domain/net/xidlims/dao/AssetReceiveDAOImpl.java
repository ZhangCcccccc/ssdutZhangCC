package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.AssetReceive;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage AssetReceive entities.
 * 
 */
@Repository("AssetReceiveDAO")
@Transactional
public class AssetReceiveDAOImpl extends AbstractJpaDao<AssetReceive> implements
		AssetReceiveDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { AssetReceive.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetReceiveDAOImpl
	 *
	 */
	public AssetReceiveDAOImpl() {
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
	 * JPQL Query - findAllAssetReceives
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAllAssetReceives() throws DataAccessException {

		return findAllAssetReceives(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssetReceives
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAllAssetReceives(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssetReceives", startResult, maxRows);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByProjectContentContaining
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByProjectContentContaining(String projectContent) throws DataAccessException {

		return findAssetReceiveByProjectContentContaining(projectContent, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByProjectContentContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByProjectContentContaining(String projectContent, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByProjectContentContaining", startResult, maxRows, projectContent);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveById
	 *
	 */
	@Transactional
	public AssetReceive findAssetReceiveById(Integer id) throws DataAccessException {

		return findAssetReceiveById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveById
	 *
	 */

	@Transactional
	public AssetReceive findAssetReceiveById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetReceiveById", startResult, maxRows, id);
			return (net.xidlims.domain.AssetReceive) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetReceiveByReceiveNo
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByReceiveNo(String receiveNo) throws DataAccessException {

		return findAssetReceiveByReceiveNo(receiveNo, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByReceiveNo
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByReceiveNo(String receiveNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByReceiveNo", startResult, maxRows, receiveNo);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByReceiveNoContaining
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByReceiveNoContaining(String receiveNo) throws DataAccessException {

		return findAssetReceiveByReceiveNoContaining(receiveNo, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByReceiveNoContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByReceiveNoContaining(String receiveNo, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByReceiveNoContaining", startResult, maxRows, receiveNo);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByProjectName
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByProjectName(String projectName) throws DataAccessException {

		return findAssetReceiveByProjectName(projectName, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByProjectName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByProjectName(String projectName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByProjectName", startResult, maxRows, projectName);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByAssetUsage
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByAssetUsage(String assetUsage) throws DataAccessException {

		return findAssetReceiveByAssetUsage(assetUsage, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByAssetUsage
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByAssetUsage(String assetUsage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByAssetUsage", startResult, maxRows, assetUsage);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByProjectNameContaining
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByProjectNameContaining(String projectName) throws DataAccessException {

		return findAssetReceiveByProjectNameContaining(projectName, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByProjectNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByProjectNameContaining(String projectName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByProjectNameContaining", startResult, maxRows, projectName);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByProjectContent
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByProjectContent(String projectContent) throws DataAccessException {

		return findAssetReceiveByProjectContent(projectContent, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByProjectContent
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByProjectContent(String projectContent, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByProjectContent", startResult, maxRows, projectContent);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByStatus
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByStatus(Integer status) throws DataAccessException {

		return findAssetReceiveByStatus(status, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByStatus
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByStatus(Integer status, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByStatus", startResult, maxRows, status);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByReceiveQuantity
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByReceiveQuantity(Integer receiveQuantity) throws DataAccessException {

		return findAssetReceiveByReceiveQuantity(receiveQuantity, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByReceiveQuantity
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByReceiveQuantity(Integer receiveQuantity, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByReceiveQuantity", startResult, maxRows, receiveQuantity);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByResult
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByResult(String result) throws DataAccessException {

		return findAssetReceiveByResult(result, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByResult
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByResult(String result, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByResult", startResult, maxRows, result);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByPrimaryKey
	 *
	 */
	@Transactional
	public AssetReceive findAssetReceiveByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetReceiveByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByPrimaryKey
	 *
	 */

	@Transactional
	public AssetReceive findAssetReceiveByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetReceiveByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.AssetReceive) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetReceiveByAssetUsageContaining
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByAssetUsageContaining(String assetUsage) throws DataAccessException {

		return findAssetReceiveByAssetUsageContaining(assetUsage, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByAssetUsageContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByAssetUsageContaining(String assetUsage, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByAssetUsageContaining", startResult, maxRows, assetUsage);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveBySaveSubmit
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveBySaveSubmit(Integer saveSubmit) throws DataAccessException {

		return findAssetReceiveBySaveSubmit(saveSubmit, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveBySaveSubmit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveBySaveSubmit(Integer saveSubmit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveBySaveSubmit", startResult, maxRows, saveSubmit);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByResultContaining
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByResultContaining(String result) throws DataAccessException {

		return findAssetReceiveByResultContaining(result, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByResultContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByResultContaining(String result, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByResultContaining", startResult, maxRows, result);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByMemContaining
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByMemContaining(String mem) throws DataAccessException {

		return findAssetReceiveByMemContaining(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByMemContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByMemContaining(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByMemContaining", startResult, maxRows, mem);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByMem
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByMem(String mem) throws DataAccessException {

		return findAssetReceiveByMem(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByMem
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByMem(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByMem", startResult, maxRows, mem);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByEndDate
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByEndDate(java.util.Calendar endDate) throws DataAccessException {

		return findAssetReceiveByEndDate(endDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByEndDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByEndDate(java.util.Calendar endDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByEndDate", startResult, maxRows, endDate);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByReceiveDate
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByReceiveDate(java.util.Calendar receiveDate) throws DataAccessException {

		return findAssetReceiveByReceiveDate(receiveDate, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByReceiveDate
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByReceiveDate(java.util.Calendar receiveDate, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByReceiveDate", startResult, maxRows, receiveDate);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetReceiveByStartData
	 *
	 */
	@Transactional
	public Set<AssetReceive> findAssetReceiveByStartData(java.util.Calendar startData) throws DataAccessException {

		return findAssetReceiveByStartData(startData, -1, -1);
	}

	/**
	 * JPQL Query - findAssetReceiveByStartData
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<AssetReceive> findAssetReceiveByStartData(java.util.Calendar startData, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetReceiveByStartData", startResult, maxRows, startData);
		return new LinkedHashSet<AssetReceive>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(AssetReceive entity) {
		return true;
	}
}
