package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.IotSharePowerOpentime;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage IotSharePowerOpentime entities.
 * 
 */
public interface IotSharePowerOpentimeDAO extends JpaDao<IotSharePowerOpentime> {

	/**
	 * JPQL Query - findAllIotSharePowerOpentimes
	 *
	 */
	public Set<IotSharePowerOpentime> findAllIotSharePowerOpentimes() throws DataAccessException;

	/**
	 * JPQL Query - findAllIotSharePowerOpentimes
	 *
	 */
	public Set<IotSharePowerOpentime> findAllIotSharePowerOpentimes(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIotSharePowerOpentimeByStartTime
	 *
	 */
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByStartTime(java.util.Calendar startTime) throws DataAccessException;

	/**
	 * JPQL Query - findIotSharePowerOpentimeByStartTime
	 *
	 */
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByStartTime(Calendar startTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIotSharePowerOpentimeByEndTime
	 *
	 */
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByEndTime(java.util.Calendar endTime) throws DataAccessException;

	/**
	 * JPQL Query - findIotSharePowerOpentimeByEndTime
	 *
	 */
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByEndTime(Calendar endTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIotSharePowerOpentimeByPrimaryKey
	 *
	 */
	public IotSharePowerOpentime findIotSharePowerOpentimeByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findIotSharePowerOpentimeByPrimaryKey
	 *
	 */
	public IotSharePowerOpentime findIotSharePowerOpentimeByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIotSharePowerOpentimeByEnable
	 *
	 */
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByEnable(Integer enable) throws DataAccessException;

	/**
	 * JPQL Query - findIotSharePowerOpentimeByEnable
	 *
	 */
	public Set<IotSharePowerOpentime> findIotSharePowerOpentimeByEnable(Integer enable, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findIotSharePowerOpentimeById
	 *
	 */
	public IotSharePowerOpentime findIotSharePowerOpentimeById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findIotSharePowerOpentimeById
	 *
	 */
	public IotSharePowerOpentime findIotSharePowerOpentimeById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}