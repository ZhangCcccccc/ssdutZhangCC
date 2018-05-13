package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.AssetReceiveAllocation;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetReceiveAllocation entities.
 * 
 */
public interface AssetReceiveAllocationDAO extends
		JpaDao<AssetReceiveAllocation> {

	/**
	 * JPQL Query - findAssetReceiveAllocationByMemContaining
	 *
	 */
	public Set<AssetReceiveAllocation> findAssetReceiveAllocationByMemContaining(String mem) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAllocationByMemContaining
	 *
	 */
	public Set<AssetReceiveAllocation> findAssetReceiveAllocationByMemContaining(String mem, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAllocationById
	 *
	 */
	public AssetReceiveAllocation findAssetReceiveAllocationById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAllocationById
	 *
	 */
	public AssetReceiveAllocation findAssetReceiveAllocationById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAllocationByPrimaryKey
	 *
	 */
	public AssetReceiveAllocation findAssetReceiveAllocationByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAllocationByPrimaryKey
	 *
	 */
	public AssetReceiveAllocation findAssetReceiveAllocationByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetReceiveAllocations
	 *
	 */
	public Set<AssetReceiveAllocation> findAllAssetReceiveAllocations() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetReceiveAllocations
	 *
	 */
	public Set<AssetReceiveAllocation> findAllAssetReceiveAllocations(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAllocationByMem
	 *
	 */
	public Set<AssetReceiveAllocation> findAssetReceiveAllocationByMem(String mem_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetReceiveAllocationByMem
	 *
	 */
	public Set<AssetReceiveAllocation> findAssetReceiveAllocationByMem(String mem_1, int startResult, int maxRows) throws DataAccessException;

}