package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.Asset;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage Asset entities.
 * 
 */
public interface AssetDAO extends JpaDao<Asset> {

	/**
	 * JPQL Query - findAssetByEnAlias
	 *
	 */
	public Set<Asset> findAssetByEnAlias(String enAlias) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByEnAlias
	 *
	 */
	public Set<Asset> findAssetByEnAlias(String enAlias, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByPrimaryKey
	 *
	 */
	public Asset findAssetByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByPrimaryKey
	 *
	 */
	public Asset findAssetByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetBySpecifications
	 *
	 */
	public Set<Asset> findAssetBySpecifications(String specifications) throws DataAccessException;

	/**
	 * JPQL Query - findAssetBySpecifications
	 *
	 */
	public Set<Asset> findAssetBySpecifications(String specifications, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByEnAliasContaining
	 *
	 */
	public Set<Asset> findAssetByEnAliasContaining(String enAlias_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByEnAliasContaining
	 *
	 */
	public Set<Asset> findAssetByEnAliasContaining(String enAlias_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByPingyin
	 *
	 */
	public Set<Asset> findAssetByPingyin(String pingyin) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByPingyin
	 *
	 */
	public Set<Asset> findAssetByPingyin(String pingyin, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByChNameContaining
	 *
	 */
	public Set<Asset> findAssetByChNameContaining(String chName) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByChNameContaining
	 *
	 */
	public Set<Asset> findAssetByChNameContaining(String chName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByLevelContaining
	 *
	 */
	public Set<Asset> findAssetByLevelContaining(String level) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByLevelContaining
	 *
	 */
	public Set<Asset> findAssetByLevelContaining(String level, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByCas
	 *
	 */
	public Set<Asset> findAssetByCas(String cas) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByCas
	 *
	 */
	public Set<Asset> findAssetByCas(String cas, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByLevel
	 *
	 */
	public Set<Asset> findAssetByLevel(String level_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByLevel
	 *
	 */
	public Set<Asset> findAssetByLevel(String level_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByEnName
	 *
	 */
	public Set<Asset> findAssetByEnName(String enName) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByEnName
	 *
	 */
	public Set<Asset> findAssetByEnName(String enName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByChName
	 *
	 */
	public Set<Asset> findAssetByChName(String chName_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByChName
	 *
	 */
	public Set<Asset> findAssetByChName(String chName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByPingyinContaining
	 *
	 */
	public Set<Asset> findAssetByPingyinContaining(String pingyin_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByPingyinContaining
	 *
	 */
	public Set<Asset> findAssetByPingyinContaining(String pingyin_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByStyleNumberContaining
	 *
	 */
	public Set<Asset> findAssetByStyleNumberContaining(String styleNumber) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByStyleNumberContaining
	 *
	 */
	public Set<Asset> findAssetByStyleNumberContaining(String styleNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetById
	 *
	 */
	public Asset findAssetById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetById
	 *
	 */
	public Asset findAssetById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetBySecurityLevel
	 *
	 */
	public Set<Asset> findAssetBySecurityLevel(Integer securityLevel) throws DataAccessException;

	/**
	 * JPQL Query - findAssetBySecurityLevel
	 *
	 */
	public Set<Asset> findAssetBySecurityLevel(Integer securityLevel, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByChAliasContaining
	 *
	 */
	public Set<Asset> findAssetByChAliasContaining(String chAlias) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByChAliasContaining
	 *
	 */
	public Set<Asset> findAssetByChAliasContaining(String chAlias, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByMem
	 *
	 */
	public Set<Asset> findAssetByMem(String mem) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByMem
	 *
	 */
	public Set<Asset> findAssetByMem(String mem, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByFlag
	 *
	 */
	public Set<Asset> findAssetByFlag(Integer flag) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByFlag
	 *
	 */
	public Set<Asset> findAssetByFlag(Integer flag, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByUnit
	 *
	 */
	public Set<Asset> findAssetByUnit(Integer unit) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByUnit
	 *
	 */
	public Set<Asset> findAssetByUnit(Integer unit, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByStyleNumber
	 *
	 */
	public Set<Asset> findAssetByStyleNumber(String styleNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByStyleNumber
	 *
	 */
	public Set<Asset> findAssetByStyleNumber(String styleNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByRank
	 *
	 */
	public Set<Asset> findAssetByRank(Integer rank) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByRank
	 *
	 */
	public Set<Asset> findAssetByRank(Integer rank, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAssets
	 *
	 */
	public Set<Asset> findAllAssets() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssets
	 *
	 */
	public Set<Asset> findAllAssets(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByCasContaining
	 *
	 */
	public Set<Asset> findAssetByCasContaining(String cas_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByCasContaining
	 *
	 */
	public Set<Asset> findAssetByCasContaining(String cas_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetBySpecificationsContaining
	 *
	 */
	public Set<Asset> findAssetBySpecificationsContaining(String specifications_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetBySpecificationsContaining
	 *
	 */
	public Set<Asset> findAssetBySpecificationsContaining(String specifications_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByEnNameContaining
	 *
	 */
	public Set<Asset> findAssetByEnNameContaining(String enName_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByEnNameContaining
	 *
	 */
	public Set<Asset> findAssetByEnNameContaining(String enName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByChAlias
	 *
	 */
	public Set<Asset> findAssetByChAlias(String chAlias_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByChAlias
	 *
	 */
	public Set<Asset> findAssetByChAlias(String chAlias_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByCategory
	 *
	 */
	public Set<Asset> findAssetByCategory(Integer category) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByCategory
	 *
	 */
	public Set<Asset> findAssetByCategory(Integer category, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByLimit
	 *
	 */
	public Set<Asset> findAssetByAssetLimit(Integer limit) throws DataAccessException;

	/**
	 * JPQL Query - findAssetByLimit
	 *
	 */
	public Set<Asset> findAssetByAssetLimit(Integer limit, int startResult, int maxRows) throws DataAccessException;

}