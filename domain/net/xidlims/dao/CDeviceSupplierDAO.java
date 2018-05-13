package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.CDeviceSupplier;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage CDeviceSupplier entities.
 * 
 */
public interface CDeviceSupplierDAO extends JpaDao<CDeviceSupplier> {

	/**
	 * JPQL Query - findCDeviceSupplierById
	 *
	 */
	public CDeviceSupplier findCDeviceSupplierById(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceSupplierById
	 *
	 */
	public CDeviceSupplier findCDeviceSupplierById(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceSupplierByDevicName
	 *
	 */
	public Set<CDeviceSupplier> findCDeviceSupplierByDevicName(String devicName) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceSupplierByDevicName
	 *
	 */
	public Set<CDeviceSupplier> findCDeviceSupplierByDevicName(String devicName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceSupplierByDevicNameContaining
	 *
	 */
	public Set<CDeviceSupplier> findCDeviceSupplierByDevicNameContaining(String devicName_1) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceSupplierByDevicNameContaining
	 *
	 */
	public Set<CDeviceSupplier> findCDeviceSupplierByDevicNameContaining(String devicName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllCDeviceSuppliers
	 *
	 */
	public Set<CDeviceSupplier> findAllCDeviceSuppliers() throws DataAccessException;

	/**
	 * JPQL Query - findAllCDeviceSuppliers
	 *
	 */
	public Set<CDeviceSupplier> findAllCDeviceSuppliers(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceSupplierByPrimaryKey
	 *
	 */
	public CDeviceSupplier findCDeviceSupplierByPrimaryKey(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findCDeviceSupplierByPrimaryKey
	 *
	 */
	public CDeviceSupplier findCDeviceSupplierByPrimaryKey(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}