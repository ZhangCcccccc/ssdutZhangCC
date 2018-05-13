package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.DeviceStatusRecord;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage DeviceStatusRecord entities.
 * 
 */
public interface DeviceStatusRecordDAO extends JpaDao<DeviceStatusRecord> {

	/**
	 * JPQL Query - findDeviceStatusRecordById
	 *
	 */
	public DeviceStatusRecord findDeviceStatusRecordById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordById
	 *
	 */
	public DeviceStatusRecord findDeviceStatusRecordById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDate
	 *
	 */
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDate(java.util.Calendar endDate) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDate
	 *
	 */
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDate(Calendar endDate, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDateAfter
	 *
	 */
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDateAfter(java.util.Calendar endDate_1) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDateAfter
	 *
	 */
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDateAfter(Calendar endDate_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllDeviceStatusRecords
	 *
	 */
	public Set<DeviceStatusRecord> findAllDeviceStatusRecords() throws DataAccessException;

	/**
	 * JPQL Query - findAllDeviceStatusRecords
	 *
	 */
	public Set<DeviceStatusRecord> findAllDeviceStatusRecords(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByStatusName
	 *
	 */
	public Set<DeviceStatusRecord> findDeviceStatusRecordByStatusName(String statusName) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByStatusName
	 *
	 */
	public Set<DeviceStatusRecord> findDeviceStatusRecordByStatusName(String statusName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByStatusNameContaining
	 *
	 */
	public Set<DeviceStatusRecord> findDeviceStatusRecordByStatusNameContaining(String statusName_1) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByStatusNameContaining
	 *
	 */
	public Set<DeviceStatusRecord> findDeviceStatusRecordByStatusNameContaining(String statusName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDateBefore
	 *
	 */
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDateBefore(java.util.Calendar endDate_2) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByEndDateBefore
	 *
	 */
	public Set<DeviceStatusRecord> findDeviceStatusRecordByEndDateBefore(Calendar endDate_2, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByPrimaryKey
	 *
	 */
	public DeviceStatusRecord findDeviceStatusRecordByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findDeviceStatusRecordByPrimaryKey
	 *
	 */
	public DeviceStatusRecord findDeviceStatusRecordByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}