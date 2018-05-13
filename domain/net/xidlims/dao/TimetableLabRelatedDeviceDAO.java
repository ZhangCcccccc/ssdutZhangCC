package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.TimetableLabRelatedDevice;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage TimetableLabRelatedDevice entities.
 * 
 */
public interface TimetableLabRelatedDeviceDAO extends
		JpaDao<TimetableLabRelatedDevice> {

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceByPrimaryKey
	 *
	 */
	public TimetableLabRelatedDevice findTimetableLabRelatedDeviceByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceByPrimaryKey
	 *
	 */
	public TimetableLabRelatedDevice findTimetableLabRelatedDeviceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableLabRelatedDevices
	 *
	 */
	public Set<TimetableLabRelatedDevice> findAllTimetableLabRelatedDevices() throws DataAccessException;

	/**
	 * JPQL Query - findAllTimetableLabRelatedDevices
	 *
	 */
	public Set<TimetableLabRelatedDevice> findAllTimetableLabRelatedDevices(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceByLabRoomDevice
	 *
	 */
	public Set<TimetableLabRelatedDevice> findTimetableLabRelatedDeviceByLabRoomDevice(Integer labRoomDevice) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceByLabRoomDevice
	 *
	 */
	public Set<TimetableLabRelatedDevice> findTimetableLabRelatedDeviceByLabRoomDevice(Integer labRoomDevice, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceById
	 *
	 */
	public TimetableLabRelatedDevice findTimetableLabRelatedDeviceById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findTimetableLabRelatedDeviceById
	 *
	 */
	public TimetableLabRelatedDevice findTimetableLabRelatedDeviceById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}