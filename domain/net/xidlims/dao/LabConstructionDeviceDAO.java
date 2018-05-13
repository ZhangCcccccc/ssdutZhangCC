package net.xidlims.dao;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.LabConstructionDevice;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabConstructionDevice entities.
 * 
 */
public interface LabConstructionDeviceDAO extends JpaDao<LabConstructionDevice> {

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceModelContaining
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceModelContaining(String deviceModel) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceModelContaining
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceModelContaining(String deviceModel, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionDevices
	 *
	 */
	public Set<LabConstructionDevice> findAllLabConstructionDevices() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabConstructionDevices
	 *
	 */
	public Set<LabConstructionDevice> findAllLabConstructionDevices(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceNameContaining
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceNameContaining(String deviceName) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceNameContaining
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceNameContaining(String deviceName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByPrimaryKey
	 *
	 */
	public LabConstructionDevice findLabConstructionDeviceByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByPrimaryKey
	 *
	 */
	public LabConstructionDevice findLabConstructionDeviceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDevicePriceRef
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDevicePriceRef(java.math.BigDecimal devicePriceRef) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDevicePriceRef
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDevicePriceRef(BigDecimal devicePriceRef, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceQuantity
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceQuantity(Integer deviceQuantity) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceQuantity
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceQuantity(Integer deviceQuantity, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByComments
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByComments(String comments) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByComments
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByComments(String comments, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByTag
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByTag(Integer tag) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByTag
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByTag(Integer tag, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceName
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceName(String deviceName_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceName
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceName(String deviceName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDevicePriceSig
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDevicePriceSig(java.math.BigDecimal devicePriceSig) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDevicePriceSig
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDevicePriceSig(BigDecimal devicePriceSig, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceModel
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceModel(String deviceModel_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByDeviceModel
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByDeviceModel(String deviceModel_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByArrivalTime
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByArrivalTime(java.util.Calendar arrivalTime) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceByArrivalTime
	 *
	 */
	public Set<LabConstructionDevice> findLabConstructionDeviceByArrivalTime(Calendar arrivalTime, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceById
	 *
	 */
	public LabConstructionDevice findLabConstructionDeviceById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabConstructionDeviceById
	 *
	 */
	public LabConstructionDevice findLabConstructionDeviceById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}