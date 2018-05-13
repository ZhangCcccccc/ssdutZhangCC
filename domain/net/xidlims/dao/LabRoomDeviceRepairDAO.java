package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabRoomDeviceRepair;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomDeviceRepair entities.
 * 
 */
public interface LabRoomDeviceRepairDAO extends JpaDao<LabRoomDeviceRepair> {

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRepairRecords
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRepairRecords(String repairRecords) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRepairRecords
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRepairRecords(String repairRecords, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRepairTime
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRepairTime(java.util.Calendar repairTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRepairTime
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRepairTime(Calendar repairTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByPrimaryKey
	 *
	 */
	public LabRoomDeviceRepair findLabRoomDeviceRepairByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByPrimaryKey
	 *
	 */
	public LabRoomDeviceRepair findLabRoomDeviceRepairByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairBySoftwareFailure
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairBySoftwareFailure(String softwareFailure) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairBySoftwareFailure
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairBySoftwareFailure(String softwareFailure, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairById
	 *
	 */
	public LabRoomDeviceRepair findLabRoomDeviceRepairById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairById
	 *
	 */
	public LabRoomDeviceRepair findLabRoomDeviceRepairById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceRepairs
	 *
	 */
	public Set<LabRoomDeviceRepair> findAllLabRoomDeviceRepairs() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDeviceRepairs
	 *
	 */
	public Set<LabRoomDeviceRepair> findAllLabRoomDeviceRepairs(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByCreateTime
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByCreateTime(java.util.Calendar createTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByCreateTime
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByCreateTime(Calendar createTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRestoreTime
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRestoreTime(java.util.Calendar restoreTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByRestoreTime
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByRestoreTime(Calendar restoreTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByHardwareFailure
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByHardwareFailure(String hardwareFailure) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByHardwareFailure
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByHardwareFailure(String hardwareFailure, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByDescription
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByDescription(String description) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceRepairByDescription
	 *
	 */
	public Set<LabRoomDeviceRepair> findLabRoomDeviceRepairByDescription(String description, int startResult, int maxRows) throws DataAccessException;

}