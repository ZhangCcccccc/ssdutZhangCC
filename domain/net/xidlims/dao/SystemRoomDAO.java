package net.xidlims.dao;

import java.util.Calendar;
import java.util.Set;

import net.xidlims.domain.SystemRoom;

import org.skyway.spring.util.dao.JpaDao;

import org.springframework.dao.DataAccessException;

/**
 * DAO to manage SystemRoom entities.
 * 
 */
public interface SystemRoomDAO extends JpaDao<SystemRoom> {

	/**
	 * JPQL Query - findSystemRoomByDepartmentNumber
	 *
	 */
	public Set<SystemRoom> findSystemRoomByDepartmentNumber(String departmentNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByDepartmentNumber
	 *
	 */
	public Set<SystemRoom> findSystemRoomByDepartmentNumber(String departmentNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomUse
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomUse(String roomUse) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomUse
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomUse(String roomUse, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByUpdatedAt
	 *
	 */
	public Set<SystemRoom> findSystemRoomByUpdatedAt(java.util.Calendar updatedAt) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByUpdatedAt
	 *
	 */
	public Set<SystemRoom> findSystemRoomByUpdatedAt(Calendar updatedAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByPrimaryKey
	 *
	 */
	public SystemRoom findSystemRoomByPrimaryKey(String roomNumber) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByPrimaryKey
	 *
	 */
	public SystemRoom findSystemRoomByPrimaryKey(String roomNumber, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomNameContaining
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomNameContaining(String roomName) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomNameContaining
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomNameContaining(String roomName, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomNumber
	 *
	 */
	public SystemRoom findSystemRoomByRoomNumber(String roomNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomNumber
	 *
	 */
	public SystemRoom findSystemRoomByRoomNumber(String roomNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomNo
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomNo(String roomNo) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomNo
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomNo(String roomNo, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByDepartmentNumberContaining
	 *
	 */
	public Set<SystemRoom> findSystemRoomByDepartmentNumberContaining(String departmentNumber_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByDepartmentNumberContaining
	 *
	 */
	public Set<SystemRoom> findSystemRoomByDepartmentNumberContaining(String departmentNumber_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemRooms
	 *
	 */
	public Set<SystemRoom> findAllSystemRooms() throws DataAccessException;

	/**
	 * JPQL Query - findAllSystemRooms
	 *
	 */
	public Set<SystemRoom> findAllSystemRooms(int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomUseContaining
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomUseContaining(String roomUse_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomUseContaining
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomUseContaining(String roomUse_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByCreatedAt
	 *
	 */
	public Set<SystemRoom> findSystemRoomByCreatedAt(java.util.Calendar createdAt) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByCreatedAt
	 *
	 */
	public Set<SystemRoom> findSystemRoomByCreatedAt(Calendar createdAt, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomName
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomName(String roomName_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomName
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomName(String roomName_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomNoContaining
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomNoContaining(String roomNo_1) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomNoContaining
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomNoContaining(String roomNo_1, int startResult, int maxRows) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomNumberContaining
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomNumberContaining(String roomNumber_2) throws DataAccessException;

	/**
	 * JPQL Query - findSystemRoomByRoomNumberContaining
	 *
	 */
	public Set<SystemRoom> findSystemRoomByRoomNumberContaining(String roomNumber_2, int startResult, int maxRows) throws DataAccessException;

}