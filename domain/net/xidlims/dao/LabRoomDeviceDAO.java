package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Set;

import net.xidlims.domain.LabRoomDevice;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomDevice entities.
 * 
 */
public interface LabRoomDeviceDAO extends JpaDao<LabRoomDevice> {

	/**
	 * JPQL Query - findLabRoomDeviceByFunction
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByFunction(String function) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByFunction
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByFunction(String function, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDevices
	 *
	 */
	public Set<LabRoomDevice> findAllLabRoomDevices() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDevices
	 *
	 */
	public Set<LabRoomDevice> findAllLabRoomDevices(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByFeatures
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByFeatures(String features) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByFeatures
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByFeatures(String features, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByDimensionalCodeContaining
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByDimensionalCodeContaining(String dimensionalCode) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByDimensionalCodeContaining
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByDimensionalCodeContaining(String dimensionalCode, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByDimensionalCode
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByDimensionalCode(String dimensionalCode_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByDimensionalCode
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByDimensionalCode(String dimensionalCode_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceById
	 *
	 */
	public LabRoomDevice findLabRoomDeviceById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceById
	 *
	 */
	public LabRoomDevice findLabRoomDeviceById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByPrice
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByPrice(java.math.BigDecimal price) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByPrice
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByPrice(BigDecimal price, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByApplications
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByApplications(String applications) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByApplications
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByApplications(String applications, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByPrimaryKey
	 *
	 */
	public LabRoomDevice findLabRoomDeviceByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByPrimaryKey
	 *
	 */
	public LabRoomDevice findLabRoomDeviceByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByIndicators
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByIndicators(String indicators) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDeviceByIndicators
	 *
	 */
	public Set<LabRoomDevice> findLabRoomDeviceByIndicators(String indicators, int startResult, int maxRows) throws DataAccessException;

}