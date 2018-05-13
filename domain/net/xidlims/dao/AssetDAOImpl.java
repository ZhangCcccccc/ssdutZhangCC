package net.xidlims.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.xidlims.domain.Asset;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

/**
 * DAO to manage Asset entities.
 * 
 */
@Repository("AssetDAO")
@Transactional
public class AssetDAOImpl extends AbstractJpaDao<Asset> implements AssetDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Asset.class }));

	/**
	 * EntityManager injected by Spring for persistence unit xidlimsConn
	 *
	 */
	@PersistenceContext(unitName = "xidlimsConn")
	private EntityManager entityManager;

	/**
	 * Instantiates a new AssetDAOImpl
	 *
	 */
	public AssetDAOImpl() {
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
	 * JPQL Query - findAssetByEnAlias
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByEnAlias(String enAlias) throws DataAccessException {

		return findAssetByEnAlias(enAlias, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByEnAlias
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByEnAlias(String enAlias, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByEnAlias", startResult, maxRows, enAlias);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByPrimaryKey
	 *
	 */
	@Transactional
	public Asset findAssetByPrimaryKey(Integer id) throws DataAccessException {

		return findAssetByPrimaryKey(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByPrimaryKey
	 *
	 */

	@Transactional
	public Asset findAssetByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetByPrimaryKey", startResult, maxRows, id);
			return (net.xidlims.domain.Asset) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetBySpecifications
	 *
	 */
	@Transactional
	public Set<Asset> findAssetBySpecifications(String specifications) throws DataAccessException {

		return findAssetBySpecifications(specifications, -1, -1);
	}

	/**
	 * JPQL Query - findAssetBySpecifications
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetBySpecifications(String specifications, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetBySpecifications", startResult, maxRows, specifications);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByEnAliasContaining
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByEnAliasContaining(String enAlias) throws DataAccessException {

		return findAssetByEnAliasContaining(enAlias, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByEnAliasContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByEnAliasContaining(String enAlias, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByEnAliasContaining", startResult, maxRows, enAlias);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByPingyin
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByPingyin(String pingyin) throws DataAccessException {

		return findAssetByPingyin(pingyin, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByPingyin
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByPingyin(String pingyin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByPingyin", startResult, maxRows, pingyin);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByChNameContaining
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByChNameContaining(String chName) throws DataAccessException {

		return findAssetByChNameContaining(chName, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByChNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByChNameContaining(String chName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByChNameContaining", startResult, maxRows, chName);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByLevelContaining
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByLevelContaining(String level) throws DataAccessException {

		return findAssetByLevelContaining(level, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByLevelContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByLevelContaining(String level, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByLevelContaining", startResult, maxRows, level);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByCas
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByCas(String cas) throws DataAccessException {

		return findAssetByCas(cas, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByCas
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByCas(String cas, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByCas", startResult, maxRows, cas);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByLevel
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByLevel(String level) throws DataAccessException {

		return findAssetByLevel(level, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByLevel
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByLevel(String level, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByLevel", startResult, maxRows, level);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByEnName
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByEnName(String enName) throws DataAccessException {

		return findAssetByEnName(enName, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByEnName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByEnName(String enName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByEnName", startResult, maxRows, enName);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByChName
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByChName(String chName) throws DataAccessException {

		return findAssetByChName(chName, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByChName
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByChName(String chName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByChName", startResult, maxRows, chName);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByPingyinContaining
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByPingyinContaining(String pingyin) throws DataAccessException {

		return findAssetByPingyinContaining(pingyin, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByPingyinContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByPingyinContaining(String pingyin, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByPingyinContaining", startResult, maxRows, pingyin);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByStyleNumberContaining
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByStyleNumberContaining(String styleNumber) throws DataAccessException {

		return findAssetByStyleNumberContaining(styleNumber, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByStyleNumberContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByStyleNumberContaining(String styleNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByStyleNumberContaining", startResult, maxRows, styleNumber);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetById
	 *
	 */
	@Transactional
	public Asset findAssetById(Integer id) throws DataAccessException {

		return findAssetById(id, -1, -1);
	}

	/**
	 * JPQL Query - findAssetById
	 *
	 */

	@Transactional
	public Asset findAssetById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findAssetById", startResult, maxRows, id);
			return (net.xidlims.domain.Asset) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * JPQL Query - findAssetBySecurityLevel
	 *
	 */
	@Transactional
	public Set<Asset> findAssetBySecurityLevel(Integer securityLevel) throws DataAccessException {

		return findAssetBySecurityLevel(securityLevel, -1, -1);
	}

	/**
	 * JPQL Query - findAssetBySecurityLevel
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetBySecurityLevel(Integer securityLevel, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetBySecurityLevel", startResult, maxRows, securityLevel);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByChAliasContaining
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByChAliasContaining(String chAlias) throws DataAccessException {

		return findAssetByChAliasContaining(chAlias, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByChAliasContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByChAliasContaining(String chAlias, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByChAliasContaining", startResult, maxRows, chAlias);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByMem
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByMem(String mem) throws DataAccessException {

		return findAssetByMem(mem, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByMem
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByMem(String mem, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByMem", startResult, maxRows, mem);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByFlag
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByFlag(Integer flag) throws DataAccessException {

		return findAssetByFlag(flag, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByFlag
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByFlag(Integer flag, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByFlag", startResult, maxRows, flag);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByUnit
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByUnit(Integer unit) throws DataAccessException {

		return findAssetByUnit(unit, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByUnit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByUnit(Integer unit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByUnit", startResult, maxRows, unit);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByStyleNumber
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByStyleNumber(String styleNumber) throws DataAccessException {

		return findAssetByStyleNumber(styleNumber, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByStyleNumber
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByStyleNumber(String styleNumber, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByStyleNumber", startResult, maxRows, styleNumber);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByRank
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByRank(Integer rank) throws DataAccessException {

		return findAssetByRank(rank, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByRank
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByRank(Integer rank, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByRank", startResult, maxRows, rank);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAllAssets
	 *
	 */
	@Transactional
	public Set<Asset> findAllAssets() throws DataAccessException {

		return findAllAssets(-1, -1);
	}

	/**
	 * JPQL Query - findAllAssets
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAllAssets(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllAssets", startResult, maxRows);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByCasContaining
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByCasContaining(String cas) throws DataAccessException {

		return findAssetByCasContaining(cas, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByCasContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByCasContaining(String cas, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByCasContaining", startResult, maxRows, cas);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetBySpecificationsContaining
	 *
	 */
	@Transactional
	public Set<Asset> findAssetBySpecificationsContaining(String specifications) throws DataAccessException {

		return findAssetBySpecificationsContaining(specifications, -1, -1);
	}

	/**
	 * JPQL Query - findAssetBySpecificationsContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetBySpecificationsContaining(String specifications, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetBySpecificationsContaining", startResult, maxRows, specifications);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByEnNameContaining
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByEnNameContaining(String enName) throws DataAccessException {

		return findAssetByEnNameContaining(enName, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByEnNameContaining
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByEnNameContaining(String enName, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByEnNameContaining", startResult, maxRows, enName);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByChAlias
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByChAlias(String chAlias) throws DataAccessException {

		return findAssetByChAlias(chAlias, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByChAlias
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByChAlias(String chAlias, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByChAlias", startResult, maxRows, chAlias);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByCategory
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByCategory(Integer category) throws DataAccessException {

		return findAssetByCategory(category, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByCategory
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByCategory(Integer category, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByCategory", startResult, maxRows, category);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * JPQL Query - findAssetByLimit
	 *
	 */
	@Transactional
	public Set<Asset> findAssetByAssetLimit(Integer limit) throws DataAccessException {

		return findAssetByAssetLimit(limit, -1, -1);
	}

	/**
	 * JPQL Query - findAssetByLimit
	 *
	 */

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Asset> findAssetByAssetLimit(Integer limit, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAssetByAssetLimit", startResult, maxRows, limit);
		return new LinkedHashSet<Asset>(query.getResultList());
	}

	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Asset entity) {
		return true;
	}
}
