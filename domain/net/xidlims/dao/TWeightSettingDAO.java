package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.TWeightSetting;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TWeightSetting entities.
 * 
 */
public interface TWeightSettingDAO extends JpaDao<TWeightSetting> {

	/**
	 * JPQL Query - findAllTWeightSettings
	 *
	 */
	public Set<TWeightSetting> findAllTWeightSettings() throws DataAccessException;

	/**
	 * JPQL Query - findAllTWeightSettings
	 *
	 */
	public Set<TWeightSetting> findAllTWeightSettings(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByPrimaryKey
	 *
	 */
	public TWeightSetting findTWeightSettingByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByPrimaryKey
	 *
	 */
	public TWeightSetting findTWeightSettingByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByModifyDate
	 *
	 */
	public Set<TWeightSetting> findTWeightSettingByModifyDate(java.util.Calendar modifyDate) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByModifyDate
	 *
	 */
	public Set<TWeightSetting> findTWeightSettingByModifyDate(Calendar modifyDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByCreateDate
	 *
	 */
	public Set<TWeightSetting> findTWeightSettingByCreateDate(java.util.Calendar createDate) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByCreateDate
	 *
	 */
	public Set<TWeightSetting> findTWeightSettingByCreateDate(Calendar createDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByTypeContaining
	 *
	 */
	public Set<TWeightSetting> findTWeightSettingByTypeContaining(String type) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByTypeContaining
	 *
	 */
	public Set<TWeightSetting> findTWeightSettingByTypeContaining(String type, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingById
	 *
	 */
	public TWeightSetting findTWeightSettingById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingById
	 *
	 */
	public TWeightSetting findTWeightSettingById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByWeight
	 *
	 */
	public Set<TWeightSetting> findTWeightSettingByWeight(java.math.BigDecimal weight) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByWeight
	 *
	 */
	public Set<TWeightSetting> findTWeightSettingByWeight(BigDecimal weight, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByType
	 *
	 */
	public Set<TWeightSetting> findTWeightSettingByType(String type_1) throws DataAccessException;

	/**
	 * JPQL Query - findTWeightSettingByType
	 *
	 */
	public Set<TWeightSetting> findTWeightSettingByType(String type_1, int startResult, int maxRows) throws DataAccessException;

}