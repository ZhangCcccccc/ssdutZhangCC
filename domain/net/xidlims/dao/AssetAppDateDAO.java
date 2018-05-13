package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.AssetAppDate;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage AssetAppDate entities.
 * 
 */
public interface AssetAppDateDAO extends JpaDao<AssetAppDate> {

	/**
	 * JPQL Query - findAllAssetAppDates
	 *
	 */
	public Set<AssetAppDate> findAllAssetAppDates() throws DataAccessException;

	/**
	 * JPQL Query - findAllAssetAppDates
	 *
	 */
	public Set<AssetAppDate> findAllAssetAppDates(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppDateById
	 *
	 */
	public AssetAppDate findAssetAppDateById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppDateById
	 *
	 */
	public AssetAppDate findAssetAppDateById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppDateByPrimaryKey
	 *
	 */
	public AssetAppDate findAssetAppDateByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppDateByPrimaryKey
	 *
	 */
	public AssetAppDate findAssetAppDateByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppDateByStartDate
	 *
	 */
	public Set<AssetAppDate> findAssetAppDateByStartDate(java.util.Calendar startDate) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppDateByStartDate
	 *
	 */
	public Set<AssetAppDate> findAssetAppDateByStartDate(Calendar startDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppDateByEndDate
	 *
	 */
	public Set<AssetAppDate> findAssetAppDateByEndDate(java.util.Calendar endDate) throws DataAccessException;

	/**
	 * JPQL Query - findAssetAppDateByEndDate
	 *
	 */
	public Set<AssetAppDate> findAssetAppDateByEndDate(Calendar endDate, int startResult, int maxRows) throws DataAccessException;

}