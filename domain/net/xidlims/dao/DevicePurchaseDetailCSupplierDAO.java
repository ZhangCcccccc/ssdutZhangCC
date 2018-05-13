package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.DevicePurchaseDetailCSupplier;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage DevicePurchaseDetailCSupplier entities.
 * 
 */
public interface DevicePurchaseDetailCSupplierDAO extends
		JpaDao<DevicePurchaseDetailCSupplier> {

	/**
	 * JPQL Query - findDevicePurchaseDetailCSupplierByPrimaryKey
	 *
	 */
	public DevicePurchaseDetailCSupplier findDevicePurchaseDetailCSupplierByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findDevicePurchaseDetailCSupplierByPrimaryKey
	 *
	 */
	public DevicePurchaseDetailCSupplier findDevicePurchaseDetailCSupplierByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllDevicePurchaseDetailCSuppliers
	 *
	 */
	public Set<DevicePurchaseDetailCSupplier> findAllDevicePurchaseDetailCSuppliers() throws DataAccessException;

	/**
	 * JPQL Query - findAllDevicePurchaseDetailCSuppliers
	 *
	 */
	public Set<DevicePurchaseDetailCSupplier> findAllDevicePurchaseDetailCSuppliers(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findDevicePurchaseDetailCSupplierById
	 *
	 */
	public DevicePurchaseDetailCSupplier findDevicePurchaseDetailCSupplierById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findDevicePurchaseDetailCSupplierById
	 *
	 */
	public DevicePurchaseDetailCSupplier findDevicePurchaseDetailCSupplierById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}