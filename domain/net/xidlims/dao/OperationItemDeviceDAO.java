package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.OperationItemDevice;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage OperationItemDevice entities.
 * 
 */
public interface OperationItemDeviceDAO extends JpaDao<OperationItemDevice> {

	/**
	 * JPQL Query - findAllOperationItemDevices
	 *
	 */
	public Set<OperationItemDevice> findAllOperationItemDevices() throws DataAccessException;

	/**
	 * JPQL Query - findAllOperationItemDevices
	 *
	 */
	public Set<OperationItemDevice> findAllOperationItemDevices(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemDeviceByPrimaryKey
	 *
	 */
	public OperationItemDevice findOperationItemDeviceByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemDeviceByPrimaryKey
	 *
	 */
	public OperationItemDevice findOperationItemDeviceByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemDeviceById
	 *
	 */
	public OperationItemDevice findOperationItemDeviceById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findOperationItemDeviceById
	 *
	 */
	public OperationItemDevice findOperationItemDeviceById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}