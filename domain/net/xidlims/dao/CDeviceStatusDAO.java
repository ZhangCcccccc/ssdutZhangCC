package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.CDeviceStatus;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CDeviceStatus entities.
 * 
 */
public interface CDeviceStatusDAO extends JpaDao<CDeviceStatus> {

	/**
	 * JPQL Query - findCDeviceStatusById
	 *
	 */
	public CDeviceStatus findCDeviceStatusById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceStatusById
	 *
	 */
	public CDeviceStatus findCDeviceStatusById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceStatusByEndDate
	 *
	 */
	public Set<CDeviceStatus> findCDeviceStatusByEndDate(java.util.Calendar endDate) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceStatusByEndDate
	 *
	 */
	public Set<CDeviceStatus> findCDeviceStatusByEndDate(Calendar endDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceStatusByStatusNameContaining
	 *
	 */
	public Set<CDeviceStatus> findCDeviceStatusByStatusNameContaining(String statusName) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceStatusByStatusNameContaining
	 *
	 */
	public Set<CDeviceStatus> findCDeviceStatusByStatusNameContaining(String statusName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceStatusByPrimaryKey
	 *
	 */
	public CDeviceStatus findCDeviceStatusByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceStatusByPrimaryKey
	 *
	 */
	public CDeviceStatus findCDeviceStatusByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCDeviceStatuss
	 *
	 */
	public Set<CDeviceStatus> findAllCDeviceStatuss() throws DataAccessException;

	/**
	 * JPQL Query - findAllCDeviceStatuss
	 *
	 */
	public Set<CDeviceStatus> findAllCDeviceStatuss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceStatusByStatusName
	 *
	 */
	public Set<CDeviceStatus> findCDeviceStatusByStatusName(String statusName_1) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceStatusByStatusName
	 *
	 */
	public Set<CDeviceStatus> findCDeviceStatusByStatusName(String statusName_1, int startResult, int maxRows) throws DataAccessException;

}