package net.xidlims.dao;

import java.util.Set;

import net.xidlims.domain.LabRoomDevicePermitUsers;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage LabRoomDevicePermitUsers entities.
 * 
 */
public interface LabRoomDevicePermitUsersDAO extends
		JpaDao<LabRoomDevicePermitUsers> {

	/**
	 * JPQL Query - findLabRoomDevicePermitUsersByPrimaryKey
	 *
	 */
	public LabRoomDevicePermitUsers findLabRoomDevicePermitUsersByPrimaryKey(Integer id) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDevicePermitUsersByPrimaryKey
	 *
	 */
	public LabRoomDevicePermitUsers findLabRoomDevicePermitUsersByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDevicePermitUserss
	 *
	 */
	public Set<LabRoomDevicePermitUsers> findAllLabRoomDevicePermitUserss() throws DataAccessException;

	/**
	 * JPQL Query - findAllLabRoomDevicePermitUserss
	 *
	 */
	public Set<LabRoomDevicePermitUsers> findAllLabRoomDevicePermitUserss(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDevicePermitUsersById
	 *
	 */
	public LabRoomDevicePermitUsers findLabRoomDevicePermitUsersById(Integer id_1) throws DataAccessException;

	/**
	 * JPQL Query - findLabRoomDevicePermitUsersById
	 *
	 */
	public LabRoomDevicePermitUsers findLabRoomDevicePermitUsersById(Integer id_1, int startResult, int maxRows) throws DataAccessException;

}